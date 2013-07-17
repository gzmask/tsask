(function(){
			var username = document.getElementById('signin_username'),
			    //passwordText = document.getElementById('passwordText'),
			password = document.getElementById('signin_password');
			if (username && password) {
				username.onfocus = function(){
					if (username.value == 'Username') {
						username.value = '';
						username.style.color = '#000';
					}
				};
				username.onblur = function(){
					if (username.value == ''){
						username.value = 'Username';
						username.style.color = '#999';
					}
				};
				password.onfocus = function(){
					if (password.value == '******') {
						password.value = '';
						password.style.color = '#000';
					}			
				};
				password.onblur = function(){
					if (password.value == ''){
						password.value = '******';
						password.style.color = '#999';
					}
				};
			}
		})();
