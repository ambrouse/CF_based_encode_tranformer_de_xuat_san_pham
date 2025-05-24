from flask import Flask,jsonify,request
import requests
from eureka_client import register_with_eureka, heartbeat
import threading
from build_model import encode_data
import pandas as pd
from build_model import create_lookup_and_tokenize,build_model1_,encode_data
import numpy as  np


app = Flask(__name__)
model_ = build_model1_()
lookup1_,lookup2_,lookup3_,lookup4_,lookup5_,lookup6_,lookup7_,tokennize1_,tokennize2_,tokennize3_,tokennize4_,tokennize5_ = create_lookup_and_tokenize()


@app.route("/")
def home():
    return "Hello, this is the home page!"


@app.route("/deep-service/api/v1/test",methods=['POST'])
def test():
    response = request.get_json()
    if not response:
        return jsonify({'error': 'No JSON data provided'}), 400
    a = response['_result']['_features_respon']
    b = []
    
    for i in a:
        b.append(list(i.values()))
    data_new = pd.DataFrame(b,columns=["_id_product","_name_user","_age","_month","_job","_sex","_total_price_buy_product","_count_click","_search_value",
                                       "_count_pay_product","month_pay_product","_total_count_product","_name_product_last_buy","_last_purchase",
                                       "_cancel","_return","_count_not_pay","_rate","_coment_product","_discount","_name_product","_type","_seasion",
                                       "_color","_brand","_style","_size","_sales","_price","_description"])
    _id = np.array(data_new['_id_product']).reshape(-1,1)
    data_new = data_new.drop('_id_product', axis=1)
    data_x1_,data_x2_,data_x3_,data_x4_,data_x5_,data_x6_,data_x7_ = encode_data(data_new,lookup1_,lookup2_,lookup3_,lookup4_,lookup5_,lookup6_,lookup7_,tokennize1_,tokennize2_,tokennize3_,tokennize4_,tokennize5_)
    y_pred_test_ = model_.predict([data_x1_,data_x2_,data_x3_,data_x4_,data_x5_,data_x6_,data_x7_])
    y_pred_test_ = np.concatenate((_id,y_pred_test_),axis=1)
    predict_ = pd.DataFrame(y_pred_test_,columns=["_id","_predict"]).sort_values(by="_predict", ascending=False).iloc[0:10]
    print(predict_)
    return predict_.to_dict(orient="records")

if __name__ == "__main__":
    register_with_eureka()
    threading.Thread(target=heartbeat, daemon=True).start()
    app.run(host="0.0.0.0",port=9007)


