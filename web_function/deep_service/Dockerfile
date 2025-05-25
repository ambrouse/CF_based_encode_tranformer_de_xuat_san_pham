# Sử dụng Python làm base image
FROM python:3.12-slim

# Set thư mục làm việc trong container
WORKDIR /app

# Copy file requirements vào container
COPY requirements.txt .

# Cài đặt thư viện
RUN pip install --no-cache-dir -r requirements.txt

# Copy toàn bộ project vào container
COPY . .

# Expose port 5000 (port mặc định của Flask)
EXPOSE 5000

# Chạy ứng dụng Flask
CMD ["python", "app.py"]