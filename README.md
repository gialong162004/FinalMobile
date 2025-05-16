# 📱 Mobile Shopping App – Giao diện hệ thống

Đây là tài liệu mô tả các chức năng và giao diện chính trong ứng dụng di động mua sắm. Hệ thống bao gồm các tính năng liên quan đến người dùng, mua hàng và quản lý thông tin cá nhân.

---

## 🧩 PHẦN 5: GIAO DIỆN HỆ THỐNG

### 5.1. Giao diện đăng ký  
- Cho phép người dùng mới tạo tài khoản.  
- Kiểm tra hợp lệ các trường: tên, email, mật khẩu, xác nhận mật khẩu.  

### 5.2. Giao diện đăng nhập  
- Nhập tên đăng nhập và mật khẩu để truy cập.  
- Hỗ trợ ghi nhớ đăng nhập (tuỳ chọn).  
- Liên kết tới chức năng "Quên mật khẩu".

### 5.3. Giao diện quên mật khẩu  
- Nhập tên đăng nhập để yêu cầu mã xác nhận.  
- Nhập mã xác nhận (OTP) để đặt lại mật khẩu mới.  
- Hiển thị lỗi khi mã sai hoặc hết hạn.


### 5.4. Giao diện trang chính  
- Hiển thị danh mục sản phẩm, sản phẩm mới, sản phẩm bán chạy.  
- Thanh tìm kiếm.  
- Chuyển đến trang sản phẩm, giỏ hàng, profile,...

---

### 5.5. Giao diện sản phẩm theo danh mục  
- Hiển thị danh sách sản phẩm thuộc 1 danh mục cụ thể.  
- Có thể xem chi tiết từng sản phẩm.

---

### 5.6. Giao diện tìm kiếm sản phẩm  
- Cho phép tìm kiếm theo tên sản phẩm.  
- Hiển thị số kết quả tìm thấy.  
- Kết quả dưới dạng lưới (recyclerview).

---

### 5.7. Giao diện giỏ hàng  
- Hiển thị các sản phẩm đã thêm vào giỏ.  
- Tăng/giảm số lượng, xoá sản phẩm.  
- Tính tổng tiền đơn hàng.

---

### 5.8. Giao diện đặt hàng  
- Cho phép xác nhận đơn hàng từ giỏ hàng.  
- Chọn địa chỉ giao hàng và phương thức thanh toán.  
- Gửi đơn hàng đến server.

---

### 5.9. Giao diện đổi địa chỉ  
- Hiển thị danh sách địa chỉ đã lưu.  
- Cho phép thêm, sửa, xoá địa chỉ.  
- Chọn địa chỉ để dùng khi đặt hàng.

---

### 5.10. Giao diện liệt kê đơn hàng  
- Hiển thị tất cả đơn hàng của người dùng.  
---

### 5.11. Giao diện đặt hàng thành công  
- Hiển thị thông báo xác nhận đặt hàng thành công.  
- Có nút quay lại trang chính hoặc xem đơn hàng.

---

### 5.12. Giao diện Profile  
- Hiển thị thông tin cá nhân: tên, email, số điện thoại,...  
- Nút truy cập chỉnh sửa thông tin hoặc đổi mật khẩu.

---

### 5.13. Giao diện edit profile  
- Cho phép chỉnh sửa họ tên, email, ảnh đại diện,...  
- Xác thực lại khi lưu thông tin.

---

### 5.14. Giao diện thay đổi mật khẩu  
- Nhập mật khẩu cũ, mật khẩu mới, xác nhận mật khẩu mới.  
- Kiểm tra hợp lệ, cập nhật mật khẩu qua API.

---

> ✅ **Ghi chú:** Các giao diện được xây dựng bằng Android Java, sử dụng Retrofit để kết nối API, và hiển thị dữ liệu bằng RecyclerView.

