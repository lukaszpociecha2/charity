document.addEventListener("DOMContentLoaded", function() {

    var newDiv = document.querySelector('#passmatcher');
    var create = document.querySelector('#create');
    var emailEmpty = document.querySelector('#emailEmpty');


    (function() {
        emailEmpty.hidden = false;
    })()


    var checkEmailEmpty = function () {
        var email = document.querySelector('#email');
        email.addEventListener('change', function (ev) {
            console.log(ev.target.value.length);
            if(ev.target.value.length===0){
                emailEmpty.hidden=false;
            } else emailEmpty.hidden=true;
        })
    };

    var passwordCheck = function(){
        let password = document.querySelector('#password');
        let password2 = document.querySelector('input[name="password2"]');

        var passwordValue;
        var passwordValue2;

        (function () {
            create.disabled = true
        }());



        password.addEventListener("keyup", function (evt) {
            passwordValue = evt.target.value;
            console.log(passwordValue);
            if (evt.target.value === passwordValue2) {
                newDiv.hidden = true;
                create.disabled = false;
            } else {
                newDiv.hidden = false;
                create.disabled = true;
            }
        });

        password2.addEventListener('keyup', function (evt) {
            console.log(evt.target.value);
            passwordValue2 = evt.target.value;
            if (evt.target.value === passwordValue) {
                newDiv.hidden = true;
                create.disabled = false;
            } else {
                newDiv.hidden = false;
                create.disabled = true;
            }
        })
    }



    var validateEmail = function(){
        document.querySelector("#email").addEventListener("change", function (evt) {

            var myEmail = {
                email : evt.target.value
            };
            var emailValid=document.querySelector("#emailValid");

            console.log(myEmail);
            $.ajax({
                type: "post",
                url: "http://localhost:8080/validate-email",
                contentType:"application/json; charset=utf-8",
                data: JSON.stringify(myEmail),
                success: function(res){
                    console.log(res);
                    if(res===false){
                        emailValid.hidden=false;
                        create.disabled=true;
                    } else {
                        emailValid.hidden=true;
                        create.disabled=false;
                    }
                },
                error:function(error)
                {
                    console.log(error);
                }
            });


        })
    }

    passwordCheck();
    validateEmail();
    checkEmailEmpty();

})