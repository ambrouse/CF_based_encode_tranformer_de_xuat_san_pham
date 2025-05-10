## PHÁT TRIỂN HỆ THỐNG KHUYẾN NGHỊ SẢN PHẨM DỰA TRÊN COLLABORATIVE FILTERING
<img src="./assets_readme/ChatGPT Image May 11, 2025, 12_59_35 AM.png" alt="!!err image loading." width="700"/>

## Giới thiệu
- Dự án hướng tới xây dựng một hệ thống gợi ý sản phẩm thông minh, giúp cá nhân hóa trải nghiệm mua sắm của từng người dùng. Thay vì chỉ dựa vào lịch sử tương tác như các hệ thống truyền thống, hệ thống này kết hợp thông tin từ cả người dùng và sản phẩm để đưa ra các gợi ý chính xác và phù hợp hơn.
- Ý tưởng cốt lõi là thu thập và biểu diễn một nhóm gồm 30 đặc trưng, bao gồm thông tin hành vi, sở thích của người dùng và đặc điểm sản phẩm. Các đặc trưng này sau đó được kết hợp lại thành từng cặp người dùng – sản phẩm, rồi đưa vào mô hình để học cách đánh giá mức độ phù hợp giữa hai bên. Việc biểu diễn riêng biệt từng loại đặc trưng và sau đó đưa vào quá trình học sâu giúp tối ưu hóa khả năng mô tả, từ đó nâng cao chất lượng gợi ý.

## Thành viên thực hiện
- Nguyễn Lê Quốc Bảo - tham gia đóng góp 100% (bản thân) (Cài đặt huấn luyện mô hình, code xử lý data, tìm kiếm nguồn data, code demo cho mô hình sau huấn luyện, viết báo cáo).

## Các vị trí trong dự án
- Ba (phân tích mô hình, tìm kiếm mô hình, tìm kiếm data, viết báo cáo).
- leader (Điều hành, quản lý dự án)
- fullstask (web)

## Hướng dẫn clone dự án

### 1. Thiếu thư mục `data` trong `./data`
Khi clone project từ GitHub về, bạn sẽ thiếu một số thư mục, bao gồm:

- Tạo thư mục `./data/data`.
- Tải file data training và file column về, đặt vào thư mục `./data/data`.

🔗 [Link data training](https://drive.google.com/drive/folders/10Xa5yRClfg0tcrF0EXb-o8jLLoYuYe34?usp=sharing)

---

### 2. Thiếu thư mục `data` và `model` trong `./web/web_builder/deep_service`

- Tạo thư mục `data` trong `./web/web_builder/deep_service`.
- Tải các file sau: `name_column.txt`, `test.txt`, `synthetic_data_0.txt`.
- Đặt các file trên vào thư mục `data` vừa tạo.

🔗 [Link data deep_service](https://drive.google.com/drive/folders/1CcGGmuG2IJ-edf2JV_gFiDMupLlum87d?usp=sharing)

- Tiếp theo, tạo thư mục `model` trong `./web/web_builder/deep_service`.
- Tải model và đặt vào thư mục `model`.

🔗 [Link model deep_service](https://drive.google.com/drive/folders/1D4BJVCL1Xen3dOzaIZpTO5OkbU_sO3YY?usp=sharing)

---

### 3. Thiếu thư mục `img` trong `./web/web_builder/react_web/react_web/public`

- Tạo thư mục `img` trong `./web/web_builder/react_web/react_web/public`.
- Tải ảnh và đặt vào thư mục `img`.

🔗 [Link ảnh trong img react](https://drive.google.com/drive/folders/1fzNUhYglnpq4-t61P4Jbctqe_UkRY6og?usp=sharing)

---

### 4. Cài đặt `node_modules` cho React

- Trong thư mục React, chạy lệnh sau để cài đặt các dependency:

```bash
npm install