app.controller('shoppingCartController', [ '$scope', 'shoppingCartFactory','$cookieStore', '$location', function($scope, shoppingCartFactory, $cookieStore, $location) {
	
	$scope.shoppingCart = [];
	$scope.host = $location.host();
	$scope.clientType = '2';
	$scope.paymentMethod = '1';
	$scope.deliveryMethod = '1';
	$scope.deliveryDetail = false;
	$scope.cardDetail = false;
	$scope.isEmpty = false;
	$scope.email = "";
	$scope.password = "";
	$scope.user;
	$scope.customer;
	$scope.order;
	$scope.cardNumber = "";
	$scope.cardExpiration = "";
	$scope.cardExpirationMonth = "";
	$scope.cardExpirationYear = "";
	$scope.cardCod = "";
	$scope.cardName = "";
	$scope.emailErrorMsg = "";
	$scope.passErrorMsg = "";
	$scope.firstNameErrorMsg = "";
	$scope.lastNameErrorMsg = "";
	$scope.cEmailErrorMsg = "";
	$scope.phoneErrorMsg = "";
	$scope.addressErrorMsg = "";
	$scope.cardNumberErrorMsg = "";
	$scope.cardCodErrorMsg = "";
	$scope.cardNameErrorMsg = "";
	$scope.emailError = false;
	$scope.passError = false;
	$scope.firstNameError = false;
	$scope.lastNameError = false;
	$scope.cEmailError = false;
	$scope.phoneError = false;
	$scope.addressError = false;
	$scope.cardNumberError = false;
	$scope.cardCodError = false;
	$scope.cardNameError = false;
	getShoppingCart();
	
	function getShoppingCart() {
		var shoppingCart = $cookieStore.get('shoppingCart');
		if(shoppingCart != null) {
			$scope.shoppingCart = shoppingCart;
			if($scope.shoppingCart.length == 0) {
				$scope.isEmpty = true;
			}
			else {
				$scope.isEmpty = false;
			}
		}
		else {
			$scope.isEmpty = true;
		}
	};
	
	$scope.isClientSelected = function(index) {
        return index === $scope.clientType;
    };
	
	$scope.quantityRange = function (sC, quantity) {
        if(quantity > 50) {
        	sC.quantity = 50;
        }
        else {
        	if(quantity < 1) {
            	sC.quantity = 1;
            }
        	else{
        		sC.quantity = quantity
        	}
        }
        $cookieStore.put('shoppingCart',$scope.shoppingCart);
    };
    
    $scope.deleteProductFromShoppingCart = function(index) {
    	$scope.shoppingCart.splice(index, 1);
    	$cookieStore.put('shoppingCart',$scope.shoppingCart);
    	if($scope.shoppingCart.length == 0) {
			$scope.isEmpty = true;
			$scope.deliveryDetail = false;
		}
		else {
			$scope.isEmpty = false;
		}
    }
    
    function validateCustomer () {
    	var ok = true;
    	if($scope.customer.firstname.length > 0) {
    		$scope.firstNameError = false;
    		$scope.firstNameErrorMsg = ""
    	}
    	else {
    		$scope.firstNameError = true;
    		$scope.firstNameErrorMsg = "First name is required."
    		ok = false;
    	}
    	
    	if($scope.customer.lastname.length > 0) {
    		$scope.lastNameError = false;
    		$scope.lastNameErrorMsg = "";
    	}
    	else {
    		$scope.lastNameError = true;
    		$scope.lastNameErrorMsg = "Last name is required.";
    		ok = false;
    	}
    	
    	if($scope.customer.email != null) {
	    	if($scope.customer.email.length > 0) {
	    		$scope.cEmailError = false;
	    		$scope.cEmailErrorMsg = "";
	    	}
	    	else {
	    		$scope.cEmailError = true;
	    		$scope.cEmailErrorMsg = "Email is required.";
	    		ok = false;
	    	}
    	}
    	else {
    		$scope.cEmailError = true;
    		$scope.cEmailErrorMsg = "Invalid email";
    		ok = false;
    	}
    	
    	if($scope.customer.phone != null) {
	    	if($scope.customer.phone.length == 10) {
	    		$scope.phoneError = false;
	    		$scope.phoneErrorMsg = "";
	    	}
	    	else {
	    		$scope.phoneError = true;
	    		$scope.phoneErrorMsg = "Phone number lenght must be 10.";
	    		ok = false;
	    	}
    	}
    	else {
    		$scope.phoneError = true;
    		$scope.phoneErrorMsg = "Invalid phone number.";
    		ok = false;
    	}
    	
    	if($scope.customer.address.length > 0) {
    		$scope.addressError = false;
    		$scope.addressErrorMsg = "";
    	}
    	else {
    		$scope.addressError = true;
    		$scope.addressErrorMsg = "Address is required.";
    		ok = false;
    	}
    	return ok;
    }
    
    $scope.finishTranzaction = function() {
    	var jsonProducts = [];
    	for(var i = 0; i < $scope.shoppingCart.length; i++) {
    		var jsonProduct = {"product" : $scope.shoppingCart[i].product, "quantity" : $scope.shoppingCart[i].quantity};
    		jsonProducts.push(jsonProduct);
		}
    	switch($scope.paymentMethod) {
	    	case '1' : 
	    		var paymentMethod = "CARD";
	    		$scope.cardDetail = true;
	    		break;
	    	case '2' : 
	    		var paymentMethod = "DOOR";
	    		$scope.cardDetail = false;
	    		break;
    	}
    	switch($scope.deliveryMethod) {
	    	case '1' : var deliveryMethod = "MAIL";
	    		break;
	    	case '2' : var deliveryMethod = "CURRIER";
	    		break;
	    	case '3' : var deliveryMethod = "PICKUP";
				break;
    	}
    	
    	var ok = validateCustomer();
    	
    	if(ok == true) {
	    	if($scope.cardDetail == false) {
		    	var order = {
		    			"customer" : $scope.customer,
		    			"products" : jsonProducts,
		    			"paymentMethod" : paymentMethod,
		    			"deliveryMethod" : deliveryMethod,
		    	}
		    	
		    	$scope.order = order;
		    	
		    	shoppingCartFactory.finishTranzaction($scope.order).success(function(result) {
		    		var ok =  true;
		    		//TO DO - mesaj ca sa inregistrat orderul si redirect la produse
		    	});
	    	}
	    	else {
	    		var order = {
		    			"customer" : $scope.customer,
		    			"products" : jsonProducts,
		    			"paymentMethod" : paymentMethod,
		    			"deliveryMethod" : deliveryMethod,
		    			"card" : null
		    	}
	
		    	$scope.order = order;
	    	}
    	}
    	else {
    		$scope.cardDetail = false;
    	}
    }
    
    $scope.cancel = function() {
    	
    	$scope.cardNumber = "";
    	$scope.cardExpiration = "";
    	$scope.cardExpirationMonth = "";
    	$scope.cardExpirationYear = "";
    	$scope.cardCod = "";
    	$scope.cardName = "";
    	
    	$scope.cardDetail = false;
    }
    
    function validationCard () {
    	var ok = true;
    	if($scope.cardNumber != null) {
	    	if($scope.cardNumber.length == 16) {
	    		$scope.cardNumberError = false;
	    		$scope.cardNumberErrorMsg = "";
	    	}
	    	else {
	    		$scope.cardNumberError = true;
	    		$scope.cardNumberErrorMsg = "Card number lenght must be 16.";
	    		ok = false;
	    	}
    	}
    	else {
    		$scope.cardNumberError = true;
    		$scope.cardNumberErrorMsg = "Invalid card number.";
    		ok = false;
    	}
    	
    	if($scope.cardCod != null) {
	    	if($scope.cardCod.length == 3) {
	    		$scope.cardCodError = false;
	    		$scope.cardCodErrorMsg = "";
	    	}
	    	else {
	    		$scope.cardCodError = true;
	    		$scope.cardCodErrorMsg = "Card cod lenght must be 3.";
	    		ok = false;
	    	}
    	}
    	else {
    		$scope.cardCodError = true;
    		$scope.cardCodErrorMsg = "Invalid card cod.";
    		ok = false;
    	}
    	
    	if($scope.cardName != null) {
	    	if($scope.cardName.length > 0) {
	    		$scope.cardNameError = false;
	    		$scope.cardNameErrorMsg = "";
	    	}
	    	else {
	    		$scope.cardNameError = true;
	    		$scope.cardNameErrorMsg = "Card name is required.";
	    		ok = false;
	    	}
    	}
    	else {
    		$scope.cardNameError = true;
    		$scope.cardNameErrorMsg = "Invalid card name. Use uppercase letters.";
    		ok = false;
    	}
    	
    	if($scope.cardExpirationMonth != null && $scope.cardExpirationYear != null) {
	    	if($scope.cardExpirationMonth.length > 0 && $scope.cardExpirationYear.length > 0) {
	    		var date = new Date($scope.cardExpirationMonth+"/"+"1/"+$scope.cardExpirationYear);
	    		var curentDate = new Date();
	    		if(curentDate.getMonth() <= date.getMonth() && curentDate.getFullYear() <= date.getFullYear()) {
		    		$scope.cardExpirationError = false;
		    		$scope.cardExpirationErrorMsg = "";
	    		}
	    		else {
	    			$scope.cardExpirationError = true;
		    		$scope.cardExpirationErrorMsg = "Your card is expired.";
		    		ok = false;
	    		}
	    	}
	    	else {
	    		$scope.cardExpirationError = true;
	    		$scope.cardExpirationErrorMsg = "Card expiration date is required.";
	    		ok = false;
	    	}
    	}
    	else {
    		$scope.cardExpirationError = true;
    		$scope.cardExpirationErrorMsg = "Invalid expiration date.";
    		ok = false;
    	}
    	return ok;
    }
    
    $scope.finishTranzactionWithCard = function() {
    	ok = validationCard();
    	if(ok == true) {
    		$scope.cardExpiration = $scope.cardExpirationMonth+"/"+$scope.cardExpirationYear;
	    	var card = {
	    			"cardnumber" : $scope.cardNumber,
	    			"cardname" : $scope.cardName,
	    			"cardcod" : $scope.cardCod,
	    			"cardexpire" : $scope.cardExpiration,
	    			"user" : $scope.customer.user
	    	}
	    	
	    	$scope.order.card = card;
	    	
	    	shoppingCartFactory.finishTranzaction($scope.order).success(function(result) {
	    		var ok =  true;
	    		$scope.cardNumber = "";
	        	$scope.cardExpiration = "";
	        	$scope.cardExpirationMonth = "";
	        	$scope.cardExpirationYear = "";
	        	$scope.cardCod = "";
	        	$scope.cardName = "";
	    	});
	    	
	    	$scope.cardDetail = false;
    	}
    }
    
    $scope.getUserInformation = function() {
    	if($scope.email != null) {
		    if($scope.email.length > 0) {
		    	if($scope.clientType == 1) {
		    		var  user = {
		        			"email" : $scope.email,
		        			"password" : $scope.password
		        	}
		    		shoppingCartFactory.getUserInformation(user).success(function(user) {
		    			if(user != "") {
		    				$scope.emailError = false;
		    				$scope.emailErrorMsg = "";
		    				if(user.password != "") {
		    					$scope.passError = false;
		    					$scope.passErrorMsg = "";
		            			shoppingCartFactory.getCustomerInformation(user).success(function(customer) {
		                			$scope.customer = customer;
		                			$scope.deliveryDetail = true;
		                		});
		        			}
		        			else {
		        				$scope.passError = true;
		    					$scope.passErrorMsg = "Incorrect password.";
		        				$scope.deliveryDetail = false;
		        			}
		    			}
		    			else {
		    				$scope.emailError = true;
		    				$scope.emailErrorMsg = "Incorrect email address.";
		    				$scope.deliveryDetail = false;
		    			}
		    		});
		    	}
		    	else {
		    		var  user = {
		        			"email" : $scope.email,
		        	}
		    		shoppingCartFactory.getUserInformation(user).success(function(user) {
		    			if(user == "") {
		    				$scope.passError = false;
		    				$scope.emailErrorMsg = "";
		    				$scope.deliveryDetail = true;
		    				var customer = {
		    						"firstname" : "",
		    						"lastname" : "",
		    						"email" : $scope.email,
		    						"phone" : "",
		    						"address" : ""
		    				}
		    				$scope.customer = customer;
		    			}
		    			else {
		    				$scope.emailError = true;
		    				$scope.emailErrorMsg = "Email address already exist. Enter your password.";
		    				$scope.deliveryDetail = false;
		    			}
		    		});
		    	}
		    }
		    else {
		    	$scope.emailError = true;
				$scope.emailErrorMsg = "Email is required.";
				$scope.deliveryDetail = false;
		    }
    	}
    	else {
    		$scope.emailError = true;
			$scope.emailErrorMsg = "Invalid email.";
			$scope.deliveryDetail = false;
    	}
    }
}]);