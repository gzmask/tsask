(function(){
			var username = document.getElementById('username'),
			    passwordText = document.getElementById('passwordText'),
				password = document.getElementById('password');
				
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
			passwordText.onfocus = function(){
				passwordText.style.display = 'none';
				password.style.display = 'inline-block';
				password.style.color = '#000';
				password.focus();
			};
			password.onblur = function(){
				if (password.value == ''){
					password.style.display = 'none';
					passwordText.value = '******';
					passwordText.style.display = 'inline-block';
				}
			};
		})();