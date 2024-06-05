$(document).ready(async function () {
    const sendEmailForUser = async () => {
        let username = $('#username').val();
        let email = $('#email').val();
        let password = $('#password').val();

        await axios
            .post("/api/accounts/send-email-for-user", {
                "username": username,
                "email": email,
                "hashedPassword": password,
                "role": 0
            })
            .then(response => {
                console.log('Response:', response.data);
                if (response.data.message === 'Email đã tồn tại') {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "Email tồn tại trong hệ thống"
                    });
                } else if (response.data.message === 'Username đã tồn tại') {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "Username tồn tại trong hệ thống"
                    });
                } else if (response.data.message === 'Call api thành công') {
                    Swal.fire({
                        icon: "success",
                        title: "Bạn sẽ được chuyển đến trang nhập otp",
                        showConfirmButton: true,
                        timer: 2000
                    })
                        .then((result) => {
                            console.log(result)
                            /* Read more about isConfirmed, isDenied below */
                            if (result.isConfirmed) {
                                window.location.href = "/confirmOTP";
                            }
                            if (result.dismiss === Swal.DismissReason.timer) {
                                window.location.href = "/confirmOTP";
                            }

                        });

                } else {
                    console.log('Thất bại:', response.data.message);

                }
            })
            .catch(error => {
                alert(error);
                console.log(error)
            })
    }
    $('#registerBtn').click(() => {
        sendEmailForUser()
    })

    const confirmOtp = async () => {
        let otp1 = $("#otp1").val();
        let otp2 = $("#otp2").val();
        let otp3 = $("#otp3").val();
        let otp4 = $("#otp4").val();
        let otp5 = $("#otp5").val();
        let otp6 = $("#otp6").val();

        const params = new URLSearchParams();
        params.append('otp1', otp1);
        params.append('otp2', otp2);
        params.append('otp3', otp3);
        params.append('otp4', otp4);
        params.append('otp5', otp5);
        params.append('otp6', otp6);

        await axios
            .post("/api/accounts/confirm-register", params)
            .then(response => {
                console.log('Response:', response.data);
                if (response.data.success) {
                    console.log('Thành công:', response.data.message);
                    // Xử lý dữ liệu trả về từ API
                    console.log('Data:', response.data.data);
                    Swal.fire({
                        icon: "success",
                        title: "Account has been saved",
                        showConfirmButton: true,
                        timer: 2000
                    }).then((result) => {
                        /* Read more about isConfirmed, isDenied below */
                        if (result.isConfirmed) {
                            window.location.href = "/login";
                        }
                        if (result.dismiss === Swal.DismissReason.timer) {
                            window.location.href = "/login";
                        }
                    });
                } else {
                    console.log('Thất bại:', response.data.message);

                }
            })
            .catch(error => {
                alert(error);
            })
    }
    $('#btnConFirmOtp').click(() => {
        confirmOtp()
    })
})



