1. Login
- Đặc tả chức năng:
+ Form đăng nhập cho phép người dùng là học viên hoặc phụ huynh nhập email và mật khẩu được cấp bởi admin để đăng nhập.
+  Nếu đăng nhập xảy ra bất cứ lỗi gì 1 Toast thông báo hiện lên thông báo chính xác lỗi đó.
![alt text](http://url/to/img.png)
2. HOme
- Đặc tả chức năng:
+   1 View thể hiện tên của tài khoản sinh viên đang sử dụng ứng dụng.
 +  1 Biểu đồ thể hiện điểm theo lớp đang học.
+   1 Danh sách các lớp học và môn học cùng với điểm của lớp đó.
+   1 số thông tin tổng quan nhất bao gồm: tổng khóa học / lớp học đang theo, tổng số tiền của tất cả khóa học, điểm trung bình tất cả các lớp đang theo.
![alt text](http://url/to/img.png)
3. Classes
- Đặc tả chức năng:
+   Danh sách các lớp lớp học đang theo học.
+   Click vào lớp học để xem chi  tiết của lớp học đó, bao gồm các thông : giảng viên của lớp, các thành viên cùng lớp. danh sách tài liệu đã được upload lên của lớp đó.

![alt text](http://url/to/img.png)
4.Schedule
- Đặc tả chức năng:
+ 2 Date Picker cho phép xem lịch học theo thời gian tìm kiếm.
+  1 danh sách lịch học đã đuợc sắp theo theo thời gian.
bao gồm thông tin về lớp, môn học, thời gian học giờ học và trạng thái của lịch học đó để sinh viên và phụ huynh có thể nắm đươcj tình hình đó. 
+ Lịch học này sử dụng cơ chế realtime của socket.io nodejs, có nghĩa là khi có tác động, hay thông báo từ phía admin hay giảng viên đến lớp học mà sinh viên có theo học, sẽ nhận được thông báo ngay lập tức và trạng thái của lịch học đó sẽ bị thay đổi mà không cần phải restart ứng dụng để cập nhật.
-> đây là 1 tính năng vượt trội và cần thiết của ứng dụng.
![alt text](http://url/to/img.png)
5. Profile
- Đặc tả chứng năng:
+   1 ImageView sử dụng thư viện picasso để load avatar của học viên.
+ Các TextView hiện thị các thông tin khách của học viên bao gồm :
 	Số lượng lớp theo học
 	Điểm trung bình các lớp
 	Tuổi	
 	Email 
+ Các button cho các chức năng khác : Đăng xuất, đổi mật khẩu.

![alt text](http://url/to/img.png)