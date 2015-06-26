app.factory('authService', [
		'$http',
		'$q',
		'$location',
		'localStorageService',
		function($http, $q, $location, localStorageService) {

			var serviceBase = $location.url();
			var authServiceFactory = {};
			var _authentication = {
				isAuth : false,
				userName : "",
				token : ""
			};

			var _saveRegistration = function(registration) {

				_logOut();

				var data = "{\"username\":\"" + registration.userName
						+ "\",\"password\":\"" + registration.password
						+ "\",\"enabled\":1}";
				return $http.put(serviceBase + '/showroom/ws/users/signup',
						data, {
							headers : {
								'Content-Type' : 'application/json'
							}
						}).then(function(response) {
					if (response.data == "")
						response = "NOT OK";
					return response;
				});
			};

			var _login = function(loginData) {
				var data = "{\"username\":\"" + loginData.userName
						+ "\",\"password\":\"" + loginData.password + "\"}";

				var deferred = $q.defer();
				serviceBase = "";
				$http.post(serviceBase + '/suppliercenter/ws/login', data, {
					headers : {
						'Content-Type' : 'application/json'
					}
				}).success(function(response) {
					localStorageService.set('authorizationData', {
						token : response,
						userName : loginData.userName
					});
					if (response != "" && response != null) {
						_authentication.isAuth = true;
						_authentication.userName = loginData.userName;
						_authentication.token = response;
					}
					deferred.resolve(response);
				}).error(function(err, status) {
					_logOut();
					deferred.reject(err);
				});

				return deferred.promise;
			};

			var _logOut = function() {

				localStorageService.remove('authorizationData');

				_authentication.isAuth = false;
				_authentication.userName = "";
			};

			var _fillAuthData = function() {

				var authData = localStorageService.get('authorizationData');
				if (authData) {
					_authentication.isAuth = true;
					_authentication.userName = authData.userName;
				}
			};

			authServiceFactory.saveRegistration = _saveRegistration;
			authServiceFactory.login = _login;
			authServiceFactory.logOut = _logOut;
			authServiceFactory.fillAuthData = _fillAuthData;
			authServiceFactory.authentication = _authentication;

			return authServiceFactory;
		} ]);