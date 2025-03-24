<?php
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST, GET");
header("Access-Control-Allow-Headers: Content-Type");

// Kết nối database
$servername = "localhost";
$username = "root";
$password = "123456";
$database = "userdb";
$conn = new mysqli($servername, $username, $password, $database);

// Kiểm tra kết nối MySQL
if ($conn->connect_error) {
    responseError("Lỗi kết nối MySQL: " . $conn->connect_error);
}

// Kiểm tra Content-Type
$contentType = isset($_SERVER["CONTENT_TYPE"]) ? trim($_SERVER["CONTENT_TYPE"]) : '';
if (stripos($contentType, "application/json") === false) {
    responseError("Content-Type phải là application/json");
}

// Đọc dữ liệu JSON từ body request
$input = file_get_contents("php://input");
$data = json_decode($input, true);

// Ghi log vào debug.txt để kiểm tra JSON
file_put_contents("debug.txt", "🔥 JSON nhận được: " . $input . "\n", FILE_APPEND);

// Kiểm tra JSON hợp lệ
if (!is_array($data)) {
    responseError("Lỗi định dạng JSON");
}

// Kiểm tra API yêu cầu
if (!isset($_GET["apicall"])) {
    responseError("API không hợp lệ");
}

$action = $_GET["apicall"];

// ✅ Xử lý ĐĂNG KÝ (signup)
if ($action == "signup") {
    if (empty($data["username"]) || empty($data["email"]) || empty($data["password"])) {
        responseError("Thiếu dữ liệu đăng ký");
    }

    $username = trim($data["username"]);
    $email = trim($data["email"]);
    $password = password_hash($data["password"], PASSWORD_BCRYPT);

    // Kiểm tra email đã tồn tại chưa
    $checkStmt = $conn->prepare("SELECT id FROM users WHERE email = ?");
    $checkStmt->bind_param("s", $email);
    $checkStmt->execute();
    $checkStmt->store_result();

    if ($checkStmt->num_rows > 0) {
        responseError("Email đã tồn tại");
    }
    $checkStmt->close();

    // Thêm tài khoản mới
    $stmt = $conn->prepare("INSERT INTO users (username, email, password) VALUES (?, ?, ?)");
    $stmt->bind_param("sss", $username, $email, $password);

    if ($stmt->execute()) {
        responseSuccess("Đăng ký thành công!");
    } else {
        responseError("Lỗi khi đăng ký tài khoản!");
    }
    $stmt->close();
}

// ✅ Xử lý ĐĂNG NHẬP (login)
elseif ($action == "login") {
    if (empty($data["email"]) || empty($data["password"])) {
        responseError("Thiếu dữ liệu đăng nhập");
    }

    $email = trim($data["email"]);
    $password = trim($data["password"]);

    // Kiểm tra email tồn tại không
    $stmt = $conn->prepare("SELECT password FROM users WHERE email = ?");
    $stmt->bind_param("s", $email);
    $stmt->execute();
    $stmt->store_result();

    if ($stmt->num_rows > 0) {
        $stmt->bind_result($hashed_password);
        $stmt->fetch();

        if (password_verify($password, $hashed_password)) {
            responseSuccess("Đăng nhập thành công!");
        } else {
            responseError("Sai mật khẩu!");
        }
    } else {
        responseError("Tài khoản không tồn tại!");
    }
    $stmt->close();
}

// ✅ API không hợp lệ
else {
    responseError("API không hợp lệ!");
}

// Đóng kết nối MySQL
$conn->close();


// 📌 **Hàm phản hồi lỗi**
function responseError($message) {
    echo json_encode(["error" => true, "message" => $message], JSON_UNESCAPED_UNICODE);
    exit();
}

// 📌 **Hàm phản hồi thành công**
function responseSuccess($message) {
    echo json_encode(["error" => false, "message" => $message], JSON_UNESCAPED_UNICODE);
    exit();
}
?>
