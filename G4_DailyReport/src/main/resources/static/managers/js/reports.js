// Set input date field is current date
// Lấy ngày hôm nay
let today = new Date();

// Định dạng ngày tháng
let year = today.getFullYear();
let month = String(today.getMonth() + 1).padStart(2, '0');
let day = String(today.getDate()).padStart(2, '0');

// Tạo chuỗi ngày tháng để sử dụng làm giá trị placeholder
let placeholderValue = year + '-' + month + '-' + day;

// Đặt giá trị placeholder cho thẻ input
document.getElementById('dateInput').setAttribute('value', placeholderValue);
