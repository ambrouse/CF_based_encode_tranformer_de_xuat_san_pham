create database test_khoa_luan_v1

go
use test_khoa_luan_v1

CREATE TABLE [_role] (
  [_id] varchar(255) PRIMARY KEY,
  [_name] nvarchar(50),
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_type] (
  [_id] varchar(255) PRIMARY KEY,
  [_name] nvarchar(50),
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_seasion] (
  [_id] varchar(255) PRIMARY KEY,
  [_name] nvarchar(20),
  [day_delete_table] datetime
)
GO

CREATE TABLE [_brand] (
  [_id] varchar(255) PRIMARY KEY,
  [_name] nvarchar(60),
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_material] (
  [_id] varchar(255) PRIMARY KEY,
  [_name] nvarchar(255),
  [day_delete_table] datetime
)
GO

CREATE TABLE [_style] (
  [_id] varchar(255) PRIMARY KEY,
  [_name] nvarchar(50),
  [_style_desription] nvarchar(max),
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_color] (
  [_id] varchar(255) PRIMARY KEY,
  [_name] nvarchar(50),
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_size] (
  [_id] varchar(255) PRIMARY KEY,
  [_name] nvarchar(50),
  [_measurement] nvarchar(255),
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_manager_size_color] (
  [_id] varchar(255) PRIMARY KEY,
  [_id_product] varchar(255),
  [_id_size] varchar(255),
  [_id_color] varchar(255),
  [_sales] int,
  _inventory int,
  _rate tinyint,
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_address] (
  [_id] varchar(255) PRIMARY KEY,
  [_id_user] varchar(255),
  [_name] nvarchar(255),
  [_status] bit,
  [_day_delete_tabel] datetime
)
GO

CREATE TABLE [_payment_method] (
  [_id] varchar(255) PRIMARY KEY,
  [_name] nvarchar(50),
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_discount_product] (
  [_id] varchar(255) PRIMARY KEY,
  [_id_manager_size_color] varchar(255),
  [_discount_percent] tinyint,
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_authen] (
  [_id] varchar(255) PRIMARY KEY,
  [_id_role] varchar(255),
  [_name] nvarchar(255),
  [_password] nvarchar(255),
  [_status_account] tinyint,
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_user] (
  [_id] varchar(255) PRIMARY KEY,
  [_id_authen] varchar(255),
  [_id_address] varchar(255),
  [_name] nvarchar(255),
  [_age] tinyint,
  [_time_of_birth] datetime,
  [_email] nvarchar(255),
  [_phone_number] varchar(11),
  [_job] nvarchar(255),
  [_mean_money_payment] int,
  _sex bit,
  [_img_user] varbinary(max),
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_staff] (
  [_id] varchar(255) PRIMARY KEY,
  [_id_authen] varchar(255),
  [_name] nvarchar(255),
  [_age] tinyint,
  [_time_of_birth] datetime,
  [_email] nvarchar(255),
  [_phone_number] varchar(11),
  [_address] nvarchar(255),
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_product] (
  [_id] varchar(255) PRIMARY KEY,
  [_id_type] varchar(255),
  [_id_brand] varchar(255),
  [_id_material] varchar(255),
  [_id_style] varchar(255),
  [_id_seasion] varchar(255),
  [_name] nvarchar(255),
  [_price] int,
  [_desription] nvarchar(2000),
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_img_product] (
  [_id] varchar(255) PRIMARY KEY,
  [_id_manager_size_color] varchar(255),
  [_img_byte] varbinary(max),
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_coment] (
  [_id] varchar(255) PRIMARY KEY,
  [_id_user] varchar(255),
  [_id_manager_size_color] varchar(255),
  [_content] nvarchar(max),
  [_day_coment] datetime,
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_img_coment] (
  [_id] varchar(255) PRIMARY KEY,
  [_id_coment] varchar(255),
  [_img_byte] varbinary(max),
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_card] (
  [_id] varchar(255) PRIMARY KEY,
  [_id_user] varchar(255),
  [_id_payment_method] varchar(255),
  [_id_address] varchar(255),
  [_status_card] tinyint,
  [_status_payment] tinyint,
  [_total_price] int,
  [_day_card] datetime,
  [_day_update_status] datetime,
  [_phone_number] varchar(11),
  [_shipping_fee] int,
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_card_mini] (
  [_id] varchar(255) PRIMARY KEY,
  [_id_card] varchar(255),
  [_id_manager_size_color] varchar(255),
  [_count] int,
  [_price] int,
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_search_history] (
  [_id] varchar(255) PRIMARY KEY,
  [_id_user] varchar(255),
  [_content] nvarchar(255),
  [_day_search] datetime,
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_feature_product] (
  [_id] varchar(255) PRIMARY KEY,
  [_id_manager_size_color] varchar(255),
  _id_user varchar(255),
  [_count_clich] int,
  [_count_pay_product] int,
  [_month_pay_] tinyint,
  [_last_purchase] int,
  [_count_cancel] int,
  [_count_return] int,
  [_count_add_card] int,
  [_day_delete_table] datetime
)
GO


CREATE TABLE [_income] (
  [_id] varchar(255) PRIMARY KEY,
  [_id_user] varchar(255),
  [_id_card] varchar(255),
  [_day_income] datetime,
  [_day_delete_table] datetime
)
GO

CREATE TABLE [_outcome] (
  [_id] varchar(255) PRIMARY KEY,
  [_id_manager_size_color] varchar(255),
  [_quantity] int,
  [_total_price] int,
  [_day_outcome] datetime,
  [_day_delete_table] datetime
)
GO

ALTER TABLE [_manager_size_color] ADD FOREIGN KEY ([_id_color]) REFERENCES [_color] ([_id])
GO

ALTER TABLE [_manager_size_color] ADD FOREIGN KEY ([_id_size]) REFERENCES [_size] ([_id])
GO

ALTER TABLE [_manager_size_color] ADD FOREIGN KEY ([_id_product]) REFERENCES [_product] ([_id])
GO

ALTER TABLE [_address] ADD FOREIGN KEY ([_id_user]) REFERENCES [_user] ([_id])
GO

ALTER TABLE [_discount_product] ADD FOREIGN KEY ([_id_manager_size_color]) REFERENCES [_manager_size_color] ([_id])
GO

ALTER TABLE [_authen] ADD FOREIGN KEY ([_id_role]) REFERENCES [_role] ([_id])
GO

ALTER TABLE [_user] ADD FOREIGN KEY ([_id_authen]) REFERENCES [_authen] ([_id])
GO

ALTER TABLE [_staff] ADD FOREIGN KEY ([_id_authen]) REFERENCES [_authen] ([_id])
GO

ALTER TABLE [_product] ADD FOREIGN KEY ([_id_brand]) REFERENCES [_brand] ([_id])
GO

ALTER TABLE [_product] ADD FOREIGN KEY ([_id_material]) REFERENCES [_material] ([_id])
GO

ALTER TABLE [_product] ADD FOREIGN KEY ([_id_seasion]) REFERENCES [_seasion] ([_id])
GO

ALTER TABLE [_product] ADD FOREIGN KEY ([_id_style]) REFERENCES [_style] ([_id])
GO

ALTER TABLE [_product] ADD FOREIGN KEY ([_id_type]) REFERENCES [_type] ([_id])
GO

ALTER TABLE [_img_product] ADD FOREIGN KEY ([_id_manager_size_color]) REFERENCES [_manager_size_color] ([_id])
GO

ALTER TABLE [_coment] ADD FOREIGN KEY ([_id_manager_size_color]) REFERENCES [_manager_size_color] ([_id])
GO

ALTER TABLE [_coment] ADD FOREIGN KEY ([_id_user]) REFERENCES [_user] ([_id])
GO

ALTER TABLE [_img_coment] ADD FOREIGN KEY ([_id_coment]) REFERENCES [_coment] ([_id])
GO

ALTER TABLE [_card] ADD FOREIGN KEY ([_id_user]) REFERENCES [_user] ([_id])
GO

ALTER TABLE [_card] ADD FOREIGN KEY ([_id_address]) REFERENCES [_address] ([_id])
GO

ALTER TABLE [_card] ADD FOREIGN KEY ([_id_payment_method]) REFERENCES [_payment_method] ([_id])
GO

ALTER TABLE [_card_mini] ADD FOREIGN KEY ([_id_card]) REFERENCES [_card] ([_id])
GO

ALTER TABLE [_card_mini] ADD FOREIGN KEY ([_id_manager_size_color]) REFERENCES [_manager_size_color] ([_id])
GO

ALTER TABLE [_search_history] ADD FOREIGN KEY ([_id_user]) REFERENCES [_user] ([_id])
GO

ALTER TABLE [_feature_product] ADD FOREIGN KEY ([_id_manager_size_color]) REFERENCES [_manager_size_color] ([_id])
GO

ALTER TABLE [_feature_product] ADD FOREIGN KEY ([_id_user]) REFERENCES [_user] ([_id])
GO

ALTER TABLE [_income] ADD FOREIGN KEY ([_id_user]) REFERENCES [_user] ([_id])
GO

ALTER TABLE [_income] ADD FOREIGN KEY ([_id_card]) REFERENCES [_card] ([_id])
GO

ALTER TABLE [_outcome] ADD FOREIGN KEY ([_id_manager_size_color]) REFERENCES [_manager_size_color] ([_id])
GO



INSERT INTO _role (_id, _name, _day_delete_table)
VALUES 
('1', N'Quản trị viên', NULL),
('2', N'Người dùng', NULL),
('3', N'Nhân viên', NULL),
('4', N'Khách', NULL),
('5', N'Đối tác', NULL);
go


INSERT INTO _type (_id, _name, _day_delete_table)
VALUES 
('1', N'Áo sơ mi', NULL),
('2', N'Quần jean', NULL),
('3', N'Áo thun', NULL),
('4', N'Áo khoác', NULL),
('5', N'Giày thể thao', NULL),
('6', N'Giày nữ', NULL),
('7', N'Túi xách', NULL),
('8', N'Balo', NULL),
('9', N'Phụ kiện', NULL),
('10', N'Đồng hồ', NULL),
('11', N'Quần jogger', NULL),
('12', N'Đầm váy', NULL),
('13', N'Quần legging', NULL),
('14', N'Áo hoodie', NULL),
('15', N'Áo gió', NULL),
('16', N'Tất', NULL),
('17', N'Thắt lưng', NULL),
('18', N'Găng tay', NULL),
('19', N'Quần short', NULL),
('20', N'Bộ đồ ngủ', NULL),
('21', N'Bộ pijama', NULL),
('22', N'Bộ vest', NULL),
('23', N'Giày nam', NULL),
('24', N'Dép nữ', NULL),
('25', N'Áo dài', NULL),
('26', N'Quần yếm', NULL),
('27', N'Mũ', NULL),
('28', N'Áo len', NULL);
go


INSERT INTO [_seasion] (_id, _name, day_delete_table)
VALUES 
('1', N'Xuân', NULL),
('2', N'Hạ', NULL),
('3', N'Thu', NULL),
('4', N'Đông', NULL);
go


INSERT INTO _brand (_id, _name, _day_delete_table)
VALUES 
('1', N'Brand B', NULL),
('2', N'Brand C', NULL),
('3', N'Brand D', NULL),
('4', N'Brand E', NULL),
('5', N'Brand F', NULL),
('6', N'Brand G', NULL),
('7', N'Brand H', NULL),
('8', N'Brand I', NULL),
('9', N'Brand J', NULL),
('10', N'Brand K', NULL),
('11', N'Brand L', NULL),
('12', N'Brand M', NULL),
('13', N'Brand N', NULL),
('14', N'Brand O', NULL),
('15', N'Brand P', NULL),
('16', N'Brand Q', NULL),
('17', N'Brand R', NULL),
('18', N'Brand S', NULL),
('19', N'Brand T', NULL),
('20', N'Brand U', NULL),
('21', N'Brand V', NULL),
('22', N'Brand W', NULL),
('23', N'Brand X', NULL),
('24', N'Brand Y', NULL),
('25', N'Brand Z', NULL),
('26', N'Brand AA', NULL),
('27', N'Brand AB', NULL),
('28', N'Brand AC', NULL),
('29', N'Brand AD', NULL),
('30', N'Brand AE', NULL),
('31', N'Brand AF', NULL),
('32', N'Brand AG', NULL),
('33', N'Brand AH', NULL);
go


INSERT INTO _material (_id, _name, day_delete_table)
VALUES 
('1', N'Cotton', NULL),
('2', N'Polyester', NULL),
('3', N'Linen', NULL),
('4', N'Satin', NULL),
('5', N'Lụa', NULL),
('6', N'Denim', NULL),
('7', N'Len', NULL),
('8', N'Nhung', NULL),
('9', N'Bamboo', NULL),
('10', N'Chiffon', NULL),
('11', N'Tweed', NULL),
('12', N'Spandex', NULL),
('13', N'Nỉ', NULL),
('14', N'Dạ', NULL),
('15', N'Canvas', NULL),
('16', N'Thiên nhiên tổng hợp', NULL),
('17', N'Viscose', NULL),
('18', N'Acrylic', NULL),
('19', N'Modal', NULL),
('20', N'Mesh', NULL);
go


INSERT INTO _style (_id, _name, _style_desription, _day_delete_table)
VALUES 
('1', N'Công sở', N'Phong cách dành cho môi trường làm việc chuyên nghiệp.', NULL),
('2', N'Casual', N'Phong cách thoải mái, phù hợp cho cuộc sống hàng ngày.', NULL),
('3', N'Streetwear', N'Phong cách đường phố, trẻ trung và cá tính.', NULL),
('4', N'Thể thao', N'Phong cách năng động, thường dùng trong các hoạt động thể thao.', NULL),
('5', N'Thanh lịch', N'Phong cách sang trọng, tinh tế, phù hợp cho sự kiện.', NULL),
('6', N'Thời trang', N'Phong cách theo xu hướng thời trang hiện đại.', NULL),
('7', N'Phụ kiện', N'Phong cách tập trung vào phụ kiện làm điểm nhấn.', NULL),
('8', N'Cá tính', N'Phong cách thể hiện sự độc đáo và khác biệt.', NULL),
('9', N'Đồ ngủ', N'Phong cách dành cho trang phục mặc ở nhà, thoải mái.', NULL),
('10', N'Bohemian', N'Phong cách tự do, hoang dã, lấy cảm hứng từ du mục.', NULL),
('11', N'Lịch lãm', N'Phong cách tinh tế, phù hợp cho doanh nhân và sự kiện.', NULL),
('12', N'Truyền thống', N'Phong cách phản ánh bản sắc văn hóa và truyền thống.', NULL),
('13', N'Vintage', N'Phong cách cổ điển, mang hơi hướng thời trang xưa.', NULL);
go


INSERT INTO _color (_id, _name, _day_delete_table)
VALUES 
('1', N'Đỏ', NULL),
('2', N'Xanh dương', NULL),
('3', N'Xanh lá', NULL),
('4', N'Vàng', NULL),
('5', N'Cam', NULL),
('6', N'Tím', NULL),
('7', N'Hồng', NULL),
('8', N'Nâu', NULL),
('9', N'Đen', NULL),
('10', N'Trắng', NULL),
('11', N'Xám', NULL),
('12', N'Bạc', NULL),
('13', N'Vàng đồng', NULL),
('14', N'Xanh ngọc', NULL),
('15', N'Xanh rêu', NULL),
('16', N'Xanh navy', NULL),
('17', N'Xanh pastel', NULL),
('18', N'Hồng pastel', NULL),
('19', N'Cam đất', NULL),
('20', N'Tím than', NULL),
('21', N'Xanh biển', NULL),
('22', N'Đỏ đô', NULL),
('23', N'Đỏ cam', NULL),
('24', N'Đỏ hồng', NULL),
('25', N'Đỏ tía', NULL),
('26', N'Xanh lá cây', NULL),
('27', N'Xanh lá mạ', NULL),
('28', N'Xanh cốm', NULL),
('29', N'Xanh da trời', NULL),
('30', N'Xanh lam', NULL),
('31', N'Xanh nước biển', NULL),
('32', N'Xanh coban', NULL),
('33', N'Xanh đậm', NULL),
('34', N'Xanh lục', NULL),
('35', N'Xanh lá non', NULL),
('36', N'Xanh chàm', NULL),
('37', N'Xanh tím than', NULL),
('38', N'Hồng cánh sen', NULL),
('39', N'Hồng đào', NULL),
('40', N'Hồng phấn', NULL),
('41', N'Hồng nhạt', NULL),
('42', N'Hồng đậm', NULL),
('43', N'Hồng tím', NULL),
('44', N'Hồng sen', NULL),
('45', N'Hồng cam', NULL),
('46', N'Hồng nude', NULL),
('47', N'Cam sáng', NULL),
('48', N'Cam tối', NULL),
('49', N'Cam vàng', NULL),
('50', N'Cam neon', NULL),
('51', N'Cam hồng', NULL),
('52', N'Tím khói', NULL),
('53', N'Tím pastel', NULL),
('54', N'Tím hồng', NULL),
('55', N'Tím xanh', NULL),
('56', N'Tím đỏ', NULL),
('57', N'Tím cà', NULL),
('58', N'Tím sẫm', NULL),
('59', N'Tím nhạt', NULL),
('60', N'Tím lợt', NULL),
('61', N'Tím thẫm', NULL),
('62', N'Tím lam', NULL),
('63', N'Nâu sẫm', NULL),
('64', N'Nâu hạt dẻ', NULL),
('65', N'Nâu đất', NULL),
('66', N'Nâu cà phê', NULL),
('67', N'Nâu nhạt', NULL),
('68', N'Nâu đỏ', NULL),
('69', N'Nâu vàng', NULL),
('70', N'Nâu cam', NULL),
('71', N'Nâu khói', NULL),
('72', N'Nâu trầm', NULL),
('73', N'Đen tuyền', NULL),
('74', N'Đen nhám', NULL),
('75', N'Đen bóng', NULL),
('76', N'Đen xám', NULL),
('77', N'Xám ghi', NULL),
('78', N'Xám tro', NULL),
('79', N'Xám bạc', NULL),
('80', N'Xám đá', NULL),
('81', N'Xám xanh', NULL),
('82', N'Xám khói', NULL),
('83', N'Xám pastel', NULL),
('84', N'Xám vàng', NULL),
('85', N'Trắng sữa', NULL),
('86', N'Trắng kem', NULL),
('87', N'Trắng tinh', NULL),
('88', N'Trắng hồng', NULL),
('89', N'Trắng ngà', NULL),
('90', N'Trắng xanh', NULL),
('91', N'Trắng bạc', NULL),
('92', N'Trắng xám', NULL),
('93', N'Vàng chanh', NULL),
('94', N'Vàng nghệ', NULL),
('95', N'Vàng nhạt', NULL),
('96', N'Vàng đất', NULL),
('97', N'Vàng cam', NULL),
('98', N'Vàng cát', NULL),
('99', N'Vàng kem', NULL),
('100', N'Vàng pastel', NULL),
('101', N'Vàng neon', NULL),
('102', N'Vàng đồng', NULL),
('103', N'Vàng chói', NULL),
('104', N'Bạc ánh kim', NULL),
('105', N'Bạc trắng', NULL),
('106', N'Bạc xám', NULL),
('107', N'Bạc xanh', NULL),
('108', N'Bạc tím', NULL);
go


INSERT INTO _size (_id, _name, _measurement, _day_delete_table)
VALUES
('1', N'M', N'Medium', NULL),
('2', N'32', N'Waist 32 inches', NULL),
('3', N'28', N'Waist 28 inches', NULL),
('4', N'XL', N'Extra Large', NULL),
('5', N'42', N'Shoe Size 42', NULL),
('6', N'38', N'Shoe Size 38', NULL),
('7', N'37', N'Shoe Size 37', NULL),
('8', N'One size', N'Universal Fit', NULL);
GO


INSERT INTO _product (
  _id, _id_type, _id_brand, _id_material, _id_style, _id_seasion,
  _name, _price, _desription, _day_delete_table
)
VALUES
('1', '5', '6', '7', '8', '2', N'Áo sơ mi nữ', 520000, N'Áo sơ mi nữ chất liệu cotton cao cấp, thiết kế thanh lịch, phù hợp cho công sở và dạo phố.', NULL),
('2', '10', '12', '9', '3', '1', N'Quần jean nam', 750000, N'Quần jean nam phong cách trẻ trung, chất liệu denim dày dặn, co giãn tốt, thoải mái vận động.', NULL),
('3', '15', '8', '2', '6', '3', N'Quần jean nữ', 730000, N'Quần jean nữ ôm dáng, thiết kế thời trang với các đường cắt hiện đại, phù hợp đi làm hoặc dạo phố.', NULL),
('4', '20', '14', '5', '12', '4', N'Áo thun nam', 250000, N'Áo thun nam basic, chất liệu cotton thoáng mát, phù hợp mặc hàng ngày.', NULL),
('5', '25', '19', '11', '9', '2', N'Áo thun nữ', 240000, N'Áo thun nữ dáng rộng, phong cách năng động, dễ phối đồ.', NULL),
('6', '27', '25', '13', '7', '1', N'Áo khoác nam', 820000, N'Áo khoác nam chống gió, lớp lót ấm áp, phù hợp cho mùa đông.', NULL),
('7', '3', '30', '15', '11', '1', N'Áo khoác nữ', 800000, N'Áo khoác nữ kiểu dáng sang trọng, giữ ấm tốt, dễ phối với nhiều phong cách.', NULL),
('8', '9', '5', '6', '10', '3', N'Giày thể thao nam', 1200000, N'Giày thể thao nam siêu nhẹ, đế cao su bám tốt, phù hợp chạy bộ và tập luyện.', NULL),
('9', '13', '18', '8', '2', '2', N'Giày thể thao nữ', 1150000, N'Giày thể thao nữ thiết kế thời trang, đế êm ái, hỗ trợ chuyển động linh hoạt.', NULL),
('10', '22', '29', '10', '5', '4', N'Giày cao gót', 1400000, N'Giày cao gót nữ sang trọng, chiều cao 7cm, phù hợp dự tiệc và công sở.', NULL),
('11', '17', '26', '14', '8', '1', N'Túi xách nữ', 900000, N'Túi xách nữ da thật, thiết kế thanh lịch, không gian rộng rãi.', NULL),
('12', '8', '21', '4', '3', '1', N'Balo nam', 500000, N'Balo nam chống nước, thiết kế nhiều ngăn tiện lợi, phù hợp đi học và đi làm.', NULL),
('13', '14', '16', '12', '6', '3', N'Balo nữ', 480000, N'Balo nữ phong cách Hàn Quốc, nhỏ gọn, phù hợp cho các bạn trẻ.', NULL),
('14', '26', '7', '1', '13', '2', N'Mắt kính thời trang', 1300000, N'Mắt kính thời trang chống tia UV, gọng kim loại chắc chắn.', NULL),
('15', '18', '11', '9', '4', '1', N'Đồng hồ nam', 3500000, N'Đồng hồ nam dây da cao cấp, chống nước, phong cách lịch lãm.', NULL),
('16', '24', '31', '7', '10', '1', N'Đồng hồ nữ', 3400000, N'Đồng hồ nữ thiết kế tinh tế, mặt kính chống trầy, phù hợp đi làm và dạo phố.', NULL),
('17', '12', '15', '16', '12', '4', N'Quần jogger nam', 450000, N'Quần jogger nam co giãn tốt, phù hợp cho thể thao và thời trang streetwear.', NULL),
('18', '28', '20', '17', '8', '3', N'Quần jogger nữ', 430000, N'Quần jogger nữ form ôm, thiết kế năng động, phù hợp tập gym và đi chơi.', NULL),
('19', '6', '9', '11', '5', '2', N'Đầm dạ hội', 1800000, N'Đầm dạ hội sang trọng, thiết kế đính đá tinh tế, phù hợp dự tiệc.', NULL),
('20', '11', '32', '18', '7', '1', N'Váy công sở', 990000, N'Váy công sở thanh lịch, tôn dáng, phù hợp đi làm.', NULL),
('21', '19', '4', '20', '9', '1', N'Quần legging nữ', 350000, N'Quần legging nữ co giãn tốt, phù hợp tập yoga và thể thao.', NULL),
('22', '16', '28', '15', '1', '3', N'Áo hoodie nam', 650000, N'Áo hoodie nam form rộng, giữ ấm tốt, phong cách đường phố.', NULL),
('23', '21', '27', '6', '11', '2', N'Áo hoodie nữ', 630000, N'Áo hoodie nữ vải dày dặn, phong cách unisex, phù hợp mùa đông.', NULL),
('24', '23', '33', '19', '13', '4', N'Áo gió nam', 780000, N'Áo gió nam chống nước, chống gió, phù hợp du lịch.', NULL),
('25', '7', '17', '14', '4', '1', N'Áo gió nữ', 760000, N'Áo gió nữ thiết kế hiện đại, dễ phối đồ, giữ ấm tốt.', NULL),
('26', '2', '22', '8', '2', '1', N'Tất nam', 50000, N'Tất nam cotton mềm mại, thấm hút tốt.', NULL),
('27', '4', '23', '5', '6', '3', N'Tất nữ', 50000, N'Tất nữ co giãn tốt, phù hợp đi giày thể thao.', NULL),
('28', '9', '10', '3', '7', '2', N'Thắt lưng nam', 400000, N'Thắt lưng nam da thật, khóa kim loại bền chắc.', NULL),
('29', '1', '13', '12', '5', '1', N'Thắt lưng nữ', 390000, N'Thắt lưng nữ thiết kế thanh lịch, phù hợp nhiều trang phục.', NULL),
('30', '5', '24', '10', '8', '4', N'Khăn choàng cổ', 250000, N'Khăn choàng cổ len mềm mại, giữ ấm tốt.', NULL),
('31', '3', '8', '2', '9', '3', N'Găng tay da', 280000, N'Găng tay da nam cao cấp, bảo vệ tốt trong mùa đông.', NULL),
('32', '11', '14', '16', '12', '1', N'Quần short nam', 550000, N'Quần short nam vải kaki, thoáng mát, phù hợp mùa hè.', NULL),
('33', '26', '9', '7', '1', '2', N'Quần short nữ', 530000, N'Quần short nữ phong cách trẻ trung, thoải mái vận động.', NULL);
go


INSERT INTO _manager_size_color (
  _id, _id_product, _id_size, _id_color,  _inventory, _sales, _day_delete_table
)
VALUES
('1', '1', '3', '15',10, 120, NULL),
('2', '1', '2', '8', 10,200, NULL),
('3', '1', '5', '20', 10,180, NULL),
('4', '2', '4', '12', 10,90, NULL),
('5', '2', '7', '25', 10,75, NULL),
('6', '3', '6', '30', 10,210, NULL),
('7', '3', '8', '45', 10,190, NULL),
('8', '4', '1', '50', 10,160, NULL),
('9', '4', '3', '55', 10,140, NULL),
('10', '5', '2', '60', 10,130, NULL),
('11', '5', '5', '65', 10,220, NULL),
('12', '6', '4', '70', 10,110, NULL),
('13', '6', '7', '75', 10,200, NULL),
('14', '7', '6', '80', 10,180, NULL),
('15', '7', '8', '85', 10,170, NULL),
('16', '8', '1', '90', 10,190, NULL),
('17', '8', '3', '95', 10,140, NULL),
('18', '9', '2', '100', 10,135, NULL),
('19', '9', '5', '105', 10,125, NULL),
('20', '10', '4', '108', 10,150, NULL),
('21', '11', '7', '5', 10,200, NULL),
('22', '12', '6', '8', 10,95, NULL),
('23', '12', '8', '12', 10,180, NULL),
('24', '13', '1', '15', 10,160, NULL),
('25', '13', '3', '20', 10,140, NULL),
('26', '14', '2', '25', 10,130, NULL),
('27', '14', '5', '30', 10,220, NULL),
('28', '15', '4', '35', 10,110, NULL),
('29', '15', '7', '40', 10,200, NULL),
('30', '16', '6', '45', 10,180, NULL),
('31', '16', '8', '50', 10,170, NULL),
('32', '17', '1', '55', 10,190, NULL),
('33', '17', '3', '60', 10,140, NULL);


INSERT INTO _authen (
  _id, _id_role, _name, _password, _status_account, _day_delete_table
)
VALUES
('1', '1', N'admin', N'Admin@123', 1, NULL),
('2', '2', N'anngu', N'NguyenA@456', 1, NULL);
go


INSERT INTO _user (
  _id, _id_authen, _name, _age, _time_of_birth, _email, _phone_number, _job, _mean_money_payment, _day_delete_table
)
VALUES
('1', '2', N'Nguyễn Văn An', 30, '1994-05-15', N'nguyenvanan@gmail.com', '0987654321', N'Kỹ sư phần mềm', 25000000, NULL);
go


INSERT INTO _address (_id, _id_user, _name, _status, _day_delete_tabel)
VALUES
('1', '1', N'123 Đường Lê Lợi, Quận 1, TP. Hồ Chí Minh', 1, NULL),
('2', '1', N'456 Đường Trần Hưng Đạo, Quận 5, TP. Hồ Chí Minh', 0, NULL),
('3', '1', N'789 Đường Phạm Văn Đồng, TP. Thủ Đức, TP. Hồ Chí Minh', 1, NULL),
('4', '1', N'101 Đường Hoàng Hoa Thám, Quận Tân Bình, TP. Hồ Chí Minh', 0, NULL),
('5', '1', N'202 Đường Nguyễn Văn Linh, Quận 7, TP. Hồ Chí Minh', 1, NULL);
GO


INSERT INTO _payment_method (_id, _name, _day_delete_table)
VALUES
('1', N'Thanh toán khi nhận hàng', NULL),
('2', N'Thanh toán qua thẻ ngân hàng', NULL),
('3', N'Thanh toán qua ví điện tử', NULL),
('4', N'Thanh toán qua QR Code', NULL),
('5', N'Thanh toán trả góp', NULL);
go


INSERT INTO _discount_product (_id, _id_manager_size_color, _discount_percent, _day_delete_table)
VALUES
('1', '5', 10, NULL),   -- Giảm 10% cho sản phẩm có ID quản lý size & màu là 5
('2', '12', 15, NULL),  -- Giảm 15% cho sản phẩm có ID quản lý size & màu là 12
('3', '20', 20, NULL),  -- Giảm 20% cho sản phẩm có ID quản lý size & màu là 20
('4', '27', 5, NULL),   -- Giảm 5% cho sản phẩm có ID quản lý size & màu là 27
('5', '33', 30, NULL);  -- Giảm 30% cho sản phẩm có ID quản lý size & màu là 33
go


INSERT INTO _staff (
  _id, _id_authen, _name, _age, _time_of_birth, _email, _phone_number, _address, _day_delete_table
) VALUES (
  '1', '1', N'Nguyễn Văn An', 30, '1995-08-15', N'an.nguyen@example.com', '0987654321', N'123 Đường Lê Lợi, Quận 1, TP.HCM', NULL
);
go


INSERT INTO _coment (_id, _id_user, _id_manager_size_color, _content, _day_delete_table)
VALUES
('1', '1', '1', N'Sản phẩm rất đẹp, chất lượng tốt!', NULL),
('2', '1', '2', N'Màu sắc không giống lắm so với ảnh trên web.', NULL),
('3', '1', '3', N'Giao hàng nhanh, đóng gói cẩn thận.', NULL),
('4', '1', '4', N'Vải mềm, mặc rất thoải mái.', NULL),
('5', '1', '5', N'Kích thước vừa vặn, rất hài lòng.', NULL),
('6', '1', '6', N'Chất liệu tốt nhưng giá hơi cao.', NULL),
('7', '1', '7', N'Mình đã mua lần thứ 2, rất thích.', NULL),
('8', '1', '8', N'Mặc lên trông rất sang, đáng tiền.', NULL),
('9', '1', '9', N'Form áo hơi rộng so với mong đợi.', NULL),
('10', '1', '10', N'Chất vải hơi mỏng nhưng vẫn đẹp.', NULL),
('11', '1', '11', N'Đóng gói kỹ, không có lỗi sản phẩm.', NULL),
('12', '1', '12', N'Sản phẩm giống mô tả, chất lượng ổn.', NULL),
('13', '1', '13', N'Giá cả hợp lý, mình sẽ giới thiệu bạn bè.', NULL),
('14', '1', '14', N'Sản phẩm đẹp nhưng giao hơi chậm.', NULL),
('15', '1', '15', N'Mình rất thích, cảm ơn shop.', NULL),
('16', '1', '16', N'Mua lần đầu nhưng rất hài lòng.', NULL),
('17', '1', '17', N'Sản phẩm đúng như hình, mặc rất đẹp.', NULL),
('18', '1', '18', N'Không bị xù lông sau khi giặt, chất lượng tốt.', NULL),
('19', '1', '19', N'Chất vải không như mong đợi lắm.', NULL),
('20', '1', '20', N'Kiểu dáng hợp thời trang, rất ưng.', NULL),
('21', '1', '21', N'Nhận hàng nhanh, nhân viên tư vấn nhiệt tình.', NULL),
('22', '1', '22', N'Shop phục vụ tốt, sẽ tiếp tục ủng hộ.', NULL),
('23', '1', '23', N'Mua để tặng bạn, bạn rất thích.', NULL),
('24', '1', '24', N'Mặc rất đẹp, đáng đồng tiền bát gạo.', NULL),
('25', '1', '25', N'Chất liệu không co giãn lắm.', NULL),
('26', '1', '26', N'Thiết kế đẹp, màu sắc nổi bật.', NULL),
('27', '1', '27', N'Đường may chắc chắn, không có chỉ thừa.', NULL),
('28', '1', '28', N'Giao hàng siêu nhanh, cảm ơn shop.', NULL),
('29', '1', '29', N'Mua về mẹ khen đẹp, rất hài lòng.', NULL),
('30', '1', '30', N'Vải hơi cứng, cần giặt vài lần mới mềm hơn.', NULL),
('31', '1', '31', N'Form chuẩn, mặc lên nhìn rất phong cách.', NULL),
('32', '1', '32', N'Shop tư vấn nhiệt tình, cảm ơn!', NULL),
('33', '1', '33', N'Sản phẩm tốt nhưng nên cải thiện bao bì.', NULL),
('34', '1', '1', N'Vải mát, thích hợp mặc mùa hè.', NULL),
('35', '1', '2', N'Sản phẩm hợp với giá tiền.', NULL),
('36', '1', '3', N'Áo đẹp nhưng đường may chưa đều.', NULL),
('37', '1', '4', N'Shop có chương trình giảm giá sẽ ủng hộ tiếp.', NULL),
('38', '1', '5', N'Chất vải không bị nhăn nhiều, rất thích.', NULL),
('39', '1', '6', N'Mua về cho bé mặc, bé rất thích.', NULL),
('40', '1', '7', N'Chất vải mềm nhưng dễ bám bụi.', NULL),
('41', '1', '8', N'Giao hàng nhanh, giá ổn.', NULL),
('42', '1', '9', N'Không có mùi lạ khi nhận hàng, rất ổn.', NULL),
('43', '1', '10', N'Vải có độ co giãn tốt, mặc thoải mái.', NULL),
('44', '1', '11', N'Kiểu dáng thời trang, đường may đẹp.', NULL),
('45', '1', '12', N'Mặc mùa hè không bị nóng, quá ổn.', NULL),
('46', '1', '13', N'Thiết kế trẻ trung, hợp xu hướng.', NULL),
('47', '1', '14', N'Shop đóng gói cẩn thận, không bị móp méo.', NULL),
('48', '1', '15', N'Form quần đẹp, vải dễ chịu.', NULL),
('49', '1', '16', N'Sản phẩm giống với mô tả, hài lòng.', NULL),
('50', '1', '17', N'Nhận hàng đúng như mong đợi.', NULL),
('51', '1', '18', N'Sản phẩm quá đẹp so với giá.', NULL),
('52', '1', '19', N'Kiểu dáng rất hợp thời trang.', NULL),
('53', '1', '20', N'Vải có hơi mỏng nhưng vẫn chấp nhận được.', NULL),
('54', '1', '21', N'Nhìn ngoài đẹp hơn trên ảnh.', NULL),
('55', '1', '22', N'Không bị phai màu sau khi giặt.', NULL),
('56', '1', '23', N'Shop phản hồi nhanh, hỗ trợ tốt.', NULL),
('57', '1', '24', N'Mặc lên nhìn rất lịch sự.', NULL),
('58', '1', '25', N'Thiết kế đẹp, nhưng hơi rộng.', NULL),
('59', '1', '26', N'Rất đáng tiền, chất vải đẹp.', NULL),
('60', '1', '27', N'Đường may cẩn thận, không bị lỗi.', NULL),
('61', '1', '28', N'Mua về tặng bạn, bạn rất thích.', NULL),
('62', '1', '29', N'Nhận hàng không có lỗi, cảm ơn shop.', NULL),
('63', '1', '30', N'Chất lượng tốt, giao nhanh.', NULL),
('64', '1', '31', N'Màu sắc đẹp, giống ảnh mô tả.', NULL),
('65', '1', '32', N'Mua 2 lần rồi, rất ưng.', NULL),
('66', '1', '33', N'Chất vải xịn, shop phục vụ nhiệt tình.', NULL),
('67', '1', '1', N'Không bị xù lông sau giặt, thích.', NULL),
('68', '1', '2', N'Mặc lên thấy rất thoải mái.', NULL),
('69', '1', '3', N'Giao đúng mẫu, đóng gói kỹ.', NULL),
('70', '1', '4', N'Thời trang và phù hợp.', NULL),
('71', '1', '5', N'Thiết kế đẹp, sẽ mua thêm.', NULL),
('72', '1', '6', N'Chất lượng tốt hơn mong đợi.', NULL),
('73', '1', '7', N'Sản phẩm xứng đáng với giá.', NULL),
('74', '1', '8', N'Mua được giá sale quá rẻ.', NULL),
('75', '1', '9', N'Kiểu dáng hợp thời, chất lượng ok.', NULL);
go


INSERT INTO _card (
    _id, _id_user, _id_payment_method, _id_address, _status_card, 
    _status_payment, _total_price, _day_card, _day_update_status, 
    _phone_number, _shipping_fee, _day_delete_table
) VALUES (
    '1', '1', '1', '1', 1, 
    0, 520000, GETDATE(), NULL, 
    '0987654321', 30000, NULL
);
go


INSERT INTO _card_mini (
    _id, _id_card, _id_manager_size_color, _price, _day_delete_table
) VALUES 
    ('1', '1', '1', 520000, NULL),
    ('2', '1', '2', 520000, NULL),
    ('3', '1', '3', 520000, NULL),
    ('4', '1', '4', 750000, NULL),
    ('5', '1', '5', 750000, NULL),
    ('6', '1', '6', 730000, NULL),
    ('7', '1', '7', 730000, NULL)
go


INSERT INTO _search_history (
    _id, _id_user, _content, _day_search, _day_delete_table
) VALUES
('1', '1', N'Áo khoác nam chống nước', DATEADD(DAY, -1, GETDATE()), NULL),
('2', '1', N'Giày sneaker trắng nam', DATEADD(DAY, -2, GETDATE()), NULL),
('3', '1', N'Quần jean nữ ống rộng', DATEADD(DAY, -3, GETDATE()), NULL),
('4', '1', N'Balo laptop chống sốc', DATEADD(DAY, -4, GETDATE()), NULL),
('5', '1', N'Đồng hồ thông minh giá rẻ', DATEADD(DAY, -5, GETDATE()), NULL),
('6', '1', N'Áo hoodie local brand', DATEADD(DAY, -6, GETDATE()), NULL),
('7', '1', N'Giày thể thao chạy bộ nam', DATEADD(DAY, -7, GETDATE()), NULL),
('8', '1', N'Kính râm thời trang nữ', DATEADD(DAY, -8, GETDATE()), NULL),
('9', '1', N'Thắt lưng da bò cao cấp', DATEADD(DAY, -9, GETDATE()), NULL),
('10', '1', N'Áo thun basic trắng', DATEADD(DAY, -10, GETDATE()), NULL),
('11', '1', N'Váy công sở nữ cao cấp', DATEADD(DAY, -11, GETDATE()), NULL),
('12', '1', N'Áo sơ mi nam Hàn Quốc', DATEADD(DAY, -12, GETDATE()), NULL),
('13', '1', N'Túi xách nữ hàng hiệu', DATEADD(DAY, -13, GETDATE()), NULL),
('14', '1', N'Quần jogger thể thao nam', DATEADD(DAY, -14, GETDATE()), NULL),
('15', '1', N'Giày cao gót công sở', DATEADD(DAY, -15, GETDATE()), NULL);
go


-- INSERT INTO _feature (
--     _id, _id_user, _id_manager_size_color, _count_clich, _count_pay_product, 
--     _month_pay_, _last_purchase, _count_cancel, _count_return, _count_add_card, _discount, _day_delete_table
-- ) VALUES
--     ('1', '1', '1', 25, 3, 3, 7, 1, 0, 5,0, NULL);
-- go


-- INSERT INTO _search_feature (
--     _id, _id_feature, _id_search_history, _day_delete_table
-- ) VALUES
--     ('1', '1', '1', NULL),
--     ('2', '1', '2', NULL),
--     ('3', '1', '3', NULL);
-- go


-- INSERT INTO _coment_feature (
--     _id, _id_feature, _id_coment, _day_delete_table
-- ) VALUES
--     ('1', '1', '1', NULL),
--     ('2', '1', '2', NULL),
--     ('3', '1', '3', NULL);
-- go


INSERT INTO _income (
    _id, _id_user, _id_card, _day_income, _day_delete_table
) 
VALUES
    ('1', NULL, NULL, '2025-03-01 08:15:00', NULL),
    ('2', NULL, NULL, '2025-03-02 12:30:00', NULL),
    ('3', NULL, NULL, '2025-03-03 14:45:00', NULL),
    ('4', NULL, NULL, '2025-03-04 10:20:00', NULL),
    ('5', NULL, NULL, '2025-03-05 09:50:00', NULL),
    ('6', NULL, NULL, '2025-03-06 16:10:00', NULL),
    ('7', NULL, NULL, '2025-03-07 11:05:00', NULL),
    ('8', NULL, NULL, '2025-03-08 13:25:00', NULL),
    ('9', NULL, NULL, '2025-03-09 17:40:00', NULL),
    ('10', NULL, NULL, '2025-03-10 19:55:00', NULL),
    ('11', NULL, NULL, '2025-03-11 08:05:00', NULL),
    ('12', NULL, NULL, '2025-03-12 15:15:00', NULL),
    ('13', NULL, NULL, '2025-03-13 18:30:00', NULL),
    ('14', NULL, NULL, '2025-03-14 09:45:00', NULL),
    ('15', NULL, NULL, '2025-03-15 20:00:00', NULL),
    ('16', NULL, NULL, '2025-03-16 07:20:00', NULL),
    ('17', NULL, NULL, '2025-03-17 14:35:00', NULL),
    ('18', NULL, NULL, '2025-03-18 12:50:00', NULL),
    ('19', NULL, NULL, '2025-03-19 10:05:00', NULL),
    ('20', NULL, NULL, '2025-03-20 17:15:00', NULL),
    ('21', NULL, NULL, '2025-03-21 22:30:00', NULL),
    ('22', NULL, NULL, '2025-03-22 11:45:00', NULL),
    ('23', NULL, NULL, '2025-03-23 16:00:00', NULL),
    ('24', NULL, NULL, '2025-03-24 08:10:00', NULL),
    ('25', NULL, NULL, '2025-03-25 14:25:00', NULL),
    ('26', NULL, NULL, '2025-03-26 19:40:00', NULL),
    ('27', NULL, NULL, '2025-03-27 13:55:00', NULL),
    ('28', NULL, NULL, '2025-03-28 21:10:00', NULL),
    ('29', NULL, NULL, '2025-03-29 09:20:00', NULL),
    ('30', NULL, NULL, '2025-03-30 18:35:00', NULL);
go


INSERT INTO _outcome (_id, _id_manager_size_color, _quantity, _total_price, _day_outcome, _day_delete_table)
VALUES
('1',  5,  2,  1040000,  '2025-03-01', NULL),
('2', 10,  3,  2250000,  '2025-03-02', NULL),
('3', 15,  4,  2920000,  '2025-03-03', NULL),
('4', 20,  1,   250000,  '2025-03-04', NULL),
('5', 25,  5,  1200000,  '2025-03-05', NULL),
('6', 27,  2,  1640000,  '2025-03-06', NULL),
('7', 30,  3,  2400000,  '2025-03-07', NULL),
('8',  9,  4,  4800000,  '2025-03-08', NULL),
('9', 13,  6,  6900000,  '2025-03-09', NULL),
('10', 22,  2,  2800000,  '2025-03-10', NULL),
('11', 17,  1,   900000,  '2025-03-11', NULL),
('12',  8,  2,  1000000,  '2025-03-12', NULL),
('13', 14,  4,  1920000,  '2025-03-13', NULL),
('14', 26,  3,  3900000,  '2025-03-14', NULL),
('15', 18,  2,  7000000,  '2025-03-15', NULL),
('16', 24,  5, 17000000,  '2025-03-16', NULL),
('17', 12,  2,   900000,  '2025-03-17', NULL),
('18', 28,  3,  1290000,  '2025-03-18', NULL),
('19',  6,  4,  7200000,  '2025-03-19', NULL),
('20', 11,  5,  4950000,  '2025-03-20', NULL),
('21', 19,  1,   350000,  '2025-03-21', NULL),
('22', 16,  3,  1950000,  '2025-03-22', NULL),
('23', 21,  2,  1260000,  '2025-03-23', NULL),
('24', 23,  4,  3120000,  '2025-03-24', NULL),
('25',  7,  3,  2280000,  '2025-03-25', NULL),
('26',  2,  6,   300000,  '2025-03-26', NULL),
('27',  4,  5,   250000,  '2025-03-27', NULL),
('28',  9,  3,  1200000,  '2025-03-28', NULL),
('29',  1,  4,  1560000,  '2025-03-29', NULL),
('30',  5,  2,   500000,  '2025-03-30', NULL);