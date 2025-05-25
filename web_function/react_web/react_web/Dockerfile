# Sử dụng image nginx để phục vụ static file
FROM nginx:alpine

# Xóa default nginx config
RUN rm -rf /usr/share/nginx/html/*

# Copy build đã có vào nginx folder
COPY dist/ /usr/share/nginx/html

# Copy file cấu hình nginx (nếu có routing client-side)
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Mở port 80
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]
