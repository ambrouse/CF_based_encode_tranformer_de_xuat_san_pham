import tensorflow as tf
from tensorflow.keras.layers import MultiHeadAttention,Embedding,Dense,Input,Dropout,GlobalAveragePooling1D,Concatenate,MultiHeadAttention,LayerNormalization
from tensorflow.keras.models import Model
import numpy as np
from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.metrics import F1Score,Recall,Precision,BinaryAccuracy
from pyvi import ViTokenizer
from tensorflow.keras.preprocessing.sequence import pad_sequences
import pandas as pd 
from tensorflow.keras import regularizers
from tensorflow.keras.constraints import max_norm
from sklearn.utils import shuffle
from sklearn.metrics import confusion_matrix
from sklearn.metrics import f1_score

def create_lookup(data):
    lookup_ = tf.keras.layers.StringLookup(output_mode="int")
    lookup_.adapt(data)
    return lookup_
def create_tokenizer(data_):
    """
        Tạo tokenizer cho dữ liệu đầu vào
    """
    tokenized_data = [ViTokenizer.tokenize(i) for i in data_]
    tokenize_ = Tokenizer(filters='!"#$%&()*+,-./:;<=>?@[\\]^`{|}~\t\n', split=" ")
    tokenize_.fit_on_texts(tokenized_data)
    return tokenize_

def create_lookup_and_tokenize():
    print("-Create lookup and tokenixe")
    try:
        df = pd.read_csv("data/test.txt", delimiter="_", header=None)
        lookup1_ = create_lookup(df.iloc[:,20])
        lookup2_ = create_lookup(df.iloc[:,21])
        lookup3_ = create_lookup(df.iloc[:,22])
        lookup4_ = create_lookup(df.iloc[:,23])
        lookup5_ = create_lookup(df.iloc[:,24])
        lookup6_ = create_lookup(df.iloc[:,25])
        lookup7_ = create_lookup(df.iloc[:,3])
        tokennize1_ = create_tokenizer(df.iloc[:,7])
        tokennize2_ = create_tokenizer(df.iloc[:,11])
        tokennize3_ = create_tokenizer(df.iloc[:,17])
        tokennize4_ = create_tokenizer(df.iloc[:,19])
        tokennize5_ = create_tokenizer(df.iloc[:,28])
        print("-Create lookup and tokenixe done!")
        return lookup1_,lookup2_,lookup3_,lookup4_,lookup5_,lookup6_,lookup7_,tokennize1_,tokennize2_,tokennize3_,tokennize4_,tokennize5_
    except(e):
        print("-Create lookup and tokenixe is err!!!!!!!!!")

    
def encryption(key_word_, data_,lookup_):
    """
        Tokenize theo từ khóa số từ tương ứng với số khóa
    """
    new_data_ = np.array(lookup_(data_)).reshape(-1,1)
    return new_data_

def tokennize(key_word_,data_,tokenize_,_pad_size):
    """
        Tokenize dữ liệu đầu vào
    """
    new_data_ = tokenize_.texts_to_sequences(data_)
    new_data_ = np.array(pad_sequences(new_data_,maxlen=_pad_size,padding="post",truncating="post"))
    return new_data_

def convert_number_log(key_word_,data_):
    prite_product_ = np.log(data_.astype(np.float64)+1).reshape(-1,1)
    return prite_product_

class PositionEmbedding(tf.keras.layers.Layer):
    def __init__(self, sequence_length, d_model,name_,trainable=True,dtype="float32"):
        super().__init__(name=name_,trainable=trainable,dtype=dtype)
        self.pos_emb = self.add_weight(
            shape=(sequence_length, d_model),  
            initializer="random_normal",
            trainable=True,
            name="position_embedding"
        )

    def call(self, inputs, training=True):
        return inputs + self.pos_emb  


class encode(tf.keras.layers.Layer):
    def __init__(self, deep_, num_head_, dropout_rate_, name_,trainable=True,dtype="float32"):
        super(encode, self).__init__(name=name_,trainable=trainable,dtype=dtype)
        self.attention_ = MultiHeadAttention(num_heads = num_head_, key_dim = deep_)
        self.dense_ = Dense(deep_,kernel_regularizer = regularizers.l2(0.001),kernel_constraint=max_norm(3), activation='relu')
        self.l1_ = LayerNormalization(epsilon=1e-6)
        self.dr1_ = Dropout(dropout_rate_)
        self.l2_ = LayerNormalization(epsilon=1e-6)
        self.dr2_ = Dropout(dropout_rate_)

    def call(self, input, training=True):
        x1_ = self.attention_(input[0],input[0])
        x3_ = self.l1_(x1_+input[1])
        x4_ = self.dense_(x3_)
        x6_ = self.l2_(x4_+x3_)
        return x6_

class emded(tf.keras.layers.Layer):
    def __init__(self, name_, deep_,trainable=True,dtype="float32"):
        super(emded, self).__init__(name=name_,trainable=trainable,dtype=dtype)
        self.input_dims = [185, 102, 108, 136, 51, 136, 1002]
        self.output_dims = [14, 11, 11, 12, 8, 12, 32]
        self.deep = deep_

        self.embed_layers = [Embedding(input_dim=dim, output_dim=out_dim) 
                             for dim, out_dim in zip(self.input_dims, self.output_dims)]
        self.dense_layers = [Dense(self.deep, use_bias=False) for _ in range(7)]
        self.concat_ = Concatenate(axis=1)

    def call(self, inputs, training=True):
        x1_, x2_, x3_, x4_, x5_,x6_,x7_ = inputs
        x_embedded = [embed(x) for embed, x in zip(self.embed_layers, [x1_, x2_, x3_, x4_, x5_,x6_,x7_])]
        x_dense = [dense(x) for dense, x in zip(self.dense_layers, x_embedded)]
        return self.concat_(x_dense)
        
class output(tf.keras.layers.Layer):
    def __init__(self, dropout_rate, deep_, name_,trainable=True,dtype="float32"):
        super(output, self).__init__(name=name_,trainable=trainable,dtype=dtype)
        self.dense_out1_ = Dense(deep_)
        self.l_out1_ = LayerNormalization(epsilon=1e-6)
        self.d_out1_ = Dropout(0.2)

    def call(self, input, training=True):
        x_ = self.dense_out1_(input)
        x_ = self.l_out1_(x_)
        x_ = self.d_out1_(x_)
        
        return x_


threshold_ = 0.6
opt = tf.keras.optimizers.Adam(learning_rate=1e-4,clipnorm=1.0)
loss = tf.keras.losses.BinaryCrossentropy(label_smoothing=0.1)

class F1Score(tf.keras.metrics.Metric):
    def __init__(self, name="f1_score", threshold=threshold_, **kwargs):
        super(F1Score, self).__init__(name=name, **kwargs)
        self.threshold = threshold
        self.true_positives = self.add_weight(name="tp", initializer="zeros")
        self.false_positives = self.add_weight(name="fp", initializer="zeros")
        self.false_negatives = self.add_weight(name="fn", initializer="zeros")

    def update_state(self, y_true, y_pred, sample_weight=None):
        y_pred = tf.cast(y_pred > self.threshold, tf.float32)  # Chuyển xác suất thành nhãn 0-1
        y_true = tf.cast(y_true, tf.float32)

        tp = tf.reduce_sum(y_true * y_pred)  # True Positives
        fp = tf.reduce_sum((1 - y_true) * y_pred)  # False Positives
        fn = tf.reduce_sum(y_true * (1 - y_pred))  # False Negatives

        self.true_positives.assign_add(tp)
        self.false_positives.assign_add(fp)
        self.false_negatives.assign_add(fn)

    def result(self):
        precision = self.true_positives / (self.true_positives + self.false_positives + tf.keras.backend.epsilon())
        recall = self.true_positives / (self.true_positives + self.false_negatives + tf.keras.backend.epsilon())
        return 2 * (precision * recall) / (precision + recall + tf.keras.backend.epsilon())

    def reset_state(self):
        self.true_positives.assign(0)
        self.false_positives.assign(0)
        self.false_negatives.assign(0)


def build_model1_():
    print("-Build model")
    input1_ = Input(shape=(4,))
    input2_ = Input(shape=(19,))
    input3_ = Input(shape=(9,))
    input4_ = Input(shape=(61,))
    input5_ = Input(shape=(3,))
    input6_ = Input(shape=(131,))
    input7_ = Input(shape=(5,))
    emded_ = emded("embeding", 256)([input1_,input2_,input3_,input4_,input5_,input6_,input7_])
    pos_ = PositionEmbedding(sequence_length=emded_.shape[1], d_model = 256,name_ = "pos_1")(emded_)
    encode1_ = encode(deep_ = 256, num_head_ = 2, dropout_rate_ = 0.2, name_="encode_1")([pos_,pos_])
    flatten_ = GlobalAveragePooling1D(name="flatten_1")(encode1_)
    out1_ = output(dropout_rate = 0.2, deep_ = 256, name_ = "ffn_output_1")(flatten_)
    out_full_ = Dense(1,activity_regularizer = regularizers.l2(0.0001),activation='sigmoid',name="output")(out1_)
    model_ = Model([input1_,input2_,input3_,input4_,input5_,input6_,input7_],out_full_,name="test_model")
    print("-Build model done!")
    print("-Load model")
    model_.load_weights("model/model2_weights.weights.h5")
    model_.compile(optimizer=opt,loss=loss,metrics=[F1Score(),BinaryAccuracy(),Recall(),Precision()])
    print("-Load done!")
    model_.summary()
    return model_;


def encode_data(data_new,lookup1_,lookup2_,lookup3_,lookup4_,lookup5_,lookup6_,lookup7_,tokennize1_,tokennize2_,tokennize3_,tokennize4_,tokennize5_):
    data_new.fillna(0, inplace=True)
    age_ = np.array(data_new.iloc[:,1]).astype(np.float64).reshape(-1,1)
    # print(f"-Tuổi của người dùng: {age_[0:5].reshape(-1)}")
    month_user_ = np.array(data_new.iloc[:,2]).astype(np.float64).reshape(-1,1)
    # print(f"-Tháng sinh của người dùng: {month_user_[0:5].reshape(-1)}")
    sex_ = np.array(data_new.iloc[:,4]+1).astype(np.float64).reshape(-1,1)
    # print(f"-Giới tính của người dùng: {sex_[0:5].reshape(-1)}")
    total_money_buy_product_ = convert_number_log(key_word_="Trung bình số tiền mỗi lần mua hàng 30 ngày gần nhất",data_=np.array(data_new.iloc[:,5]))
    number_click_ = np.array(data_new.iloc[:,6]+1).astype(np.float64).reshape(-1,1)
    # print(f"-Số lần click vào sản phẩm: {number_click_[0:5].reshape(-1)}")
    number_buy_ = np.array(data_new.iloc[:,8]+1).astype(np.float64).reshape(-1,1)
    # print(f"-Số lần mua: {number_buy_[0:5].reshape(-1)}")
    month_buy_product_ = np.array(data_new.iloc[:,9]).astype(np.float64).reshape(-1,1)
    # print(f"-Tháng mua sản phẩm: {month_buy_product_[0:5].reshape(-1)}")
    total_buy_product_ = np.array(data_new.iloc[:,10]).astype(np.float64).reshape(-1,1)
    # print(f"-Tổng số lượng sản phẩm mà người dùng mua trong 30 ngày gần nhất: {total_buy_product_[0:5].reshape(-1)}")
    number_last_day_buy_product_ = np.array(data_new.iloc[:,12]).astype(np.float64).reshape(-1,1)
    # print(f"-Khoản cách mua sản phẩm này gần nhất: {number_last_day_buy_product_[0:5].reshape(-1)}")
    number_cancel_product_ = np.array(data_new.iloc[:,13]+1).astype(np.float64).reshape(-1,1)
    # print(f"-Số lần hủy sản phẩm: {number_cancel_product_[0:5].reshape(-1)}")
    number_return_product_ = np.array(data_new.iloc[:,14]+1).astype(np.float64).reshape(-1,1)
    # print(f"-Số lần trả sản phẩm: {number_return_product_[0:5].reshape(-1)}")
    number_not_buy_product_ = np.array(data_new.iloc[:,15]+1).astype(np.float64).reshape(-1,1)
    # print(f"-Số lần thêm nhưng không mua sản phẩm: {number_not_buy_product_[0:5].reshape(-1)}")
    rate_product_ = np.array(data_new.iloc[:,16]+1).astype(np.float64).reshape(-1,1)
    # print(f"-Số điểm sản phẩm: {rate_product_[0:5].reshape(-1)}")
    discount_ = np.array(data_new.iloc[:,18]+1).astype(np.float64).reshape(-1,1)
    # print(f"-Giảm giá: {discount_[0:5].reshape(-1)}")
    sales_product_ = np.array(data_new.iloc[:,26]+1).astype(np.float64).reshape(-1,1)
    # print(f"-Lượt bán sản phẩm trong 30 ngày gần nhất: {sales_product_[0:5].reshape(-1)}")
    price_product_ = convert_number_log(key_word_="Giá tiền sản phẩm",data_=np.array(data_new.iloc[:,27]))
    search_ = tokennize("3 lịch sử tìm kiếm gần nhất",data_new.iloc[:,7],tokennize1_,12)
    name_product_buy_ = tokennize("Tên sản phẩm mua lần gần nhất",data_new.iloc[:,11],tokennize2_,3)
    coment_product_ = tokennize("3 Bình luận về sản phẩm",data_new.iloc[:,17],tokennize3_,60)
    name_product_ = tokennize("-Tên sản phẩm",data_new.iloc[:,19],tokennize4_,3)
    desription_ = tokennize("Mô tả sản phẩm",data_new.iloc[:,28],tokennize5_,131)
    type_ = encryption("Loại sản phẩm",data_new.iloc[:,20],lookup1_)
    seasion_ = encryption("Mùa ra mắt sản phẩm",data_new.iloc[:,21],lookup2_)
    color_ = encryption("Màu sản phẩm",data_new.iloc[:,22],lookup3_)
    brand_ = encryption("Thương hiệu sản phẩm",data_new.iloc[:,23],lookup4_)
    style_ = encryption("Phong cách sản phẩm",data_new.iloc[:,24],lookup5_)
    size_ = encryption("Size sản phẩm",data_new.iloc[:,25],lookup6_)
    job_ = encryption("Nghề nghiệp của người dùng",data_new.iloc[:,3],lookup7_)

    data_x1_ = np.concatenate((age_,
                           month_user_,
                           job_,
                           sex_,),axis=1)
    
    total_money_buy_product_ = np.clip(total_money_buy_product_, a_min=0, a_max=101)
    number_click_ = np.clip(number_click_, a_min=0, a_max=101)
    search_ = np.clip(search_, a_min=0, a_max=101)
    number_buy_ = np.clip(number_buy_, a_min=0, a_max=101)
    number_last_day_buy_product_ = np.clip(number_last_day_buy_product_, a_min=0, a_max=101)
    number_cancel_product_ = np.clip(number_cancel_product_, a_min=0, a_max=101)
    number_not_buy_product_ = np.clip(number_not_buy_product_, a_min=0, a_max=101)
    number_return_product_ = np.clip(number_return_product_, a_min=0, a_max=101)
    data_x2_ = np.concatenate((
                            total_money_buy_product_,
                            number_click_,
                            search_,
                            number_buy_,
                            number_last_day_buy_product_,
                            number_cancel_product_,
                            number_not_buy_product_,
                            number_return_product_
                            ),axis=1)
    data_x3_ = np.concatenate((
                            name_product_,
                            type_,
                            seasion_,
                            color_,
                            brand_,
                            style_,
                            size_),axis=1)
    data_x4_ = np.concatenate((
                            rate_product_,
                            coment_product_),axis=1)
    
    total_buy_product_ = np.clip(total_buy_product_, a_min=0, a_max=50)
    price_product_ = np.clip(price_product_, a_min=0, a_max=50)
    data_x5_ = np.concatenate((
                            total_buy_product_,
                            price_product_,
                            discount_),axis=1)
    data_x6_ = np.array(desription_)
    sales_product_ = np.clip(sales_product_, a_min=0, a_max=1002)
    data_x7_ = np.concatenate((        
                            month_buy_product_,
                            name_product_buy_,
                            sales_product_,
                            ),axis=1)
    return data_x1_,data_x2_,data_x3_,data_x4_,data_x5_,data_x6_,data_x7_