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
				token : "",
				regRight:false
			};

			var _saveRegistration = function(registration) {

				var data = "{\"username\":\"" + registration.userName
						+ "\",\"password\":\"" + registration.password
						+ "\"}";
				serviceBase = "";
				return $http.put(serviceBase + '/suppliercenter/ws/resources/register',
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
				}).success(
						function(response) {
							localStorageService.set('authorizationData', {
								token : response,
								userName : loginData.userName
							});
							if (response != "Incorrect username!"
									&& response != "Incorrect password!"
									&& response != null && response != "") {
								_authentication.isAuth = true;
								_authentication.userName = loginData.userName;
								_authentication.token = response;
								_register(response);
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
				_authentication.regRight=false;
			};
			//aflare rol user pentru optiunea de register
			var _register=	function(token){
				return $http.post(serviceBase + '/suppliercenter/ws/resources/authorities/isAdmin',
						token).then(function(regResponse) {
						debugger;
				if (regResponse.data.authority == "ADMIN")
					_authentication.regRight=true;
			});
			}
			
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