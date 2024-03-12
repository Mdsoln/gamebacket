Prerequiments:
  .java jdk version 21(Oracle OpenJDK version 21.0.2) and above
  .apache-maven version 3.3.1 and above
  .postgresql database
 -Create an account in clickSend platform, add phone numbers of  suppliers. Add your clickSend-username and clickSend-apikey in application.yml
 -You may need a businnes email or normal email using less secure app to email users with new reset password.
 -Add your email and password in MailConfiguration class
 -Also add your supplier email in Email service class
 -You may need to have postman tool for testing APIs
 
Cloning the Project:
 - git clone https://github.com/Mdsoln/gamebacket.git
 -navigate to the project in command line and run
    mvn clean package command
 -run the test classes or the entire project
Further review the project stucture, and you may need to update the project dependencies etc.   

   gamebacket APIs Documentation:
   by: Muddy Ramadhan
   email: muddyfakih98@gmail.com
   no: 0717611117/0682948753
   
	      Admin APIs:
	      main_path: '/api/v1/admin'
	   
	    publishGameToServer:
	     Endpoint: /publishGame (POST)

		Description: This API endpoint allows authorized users (presumably administrators) to publish a new game to the server.

		Request Parameters:

		.gameTitle (String, required): The title of the game.
		.gamePlatforms (String, optional): A comma-separated list of platforms the game is available on.
		.actualPrice (float, required): The full price of the game.
		.discountPrice (float, required): The discounted price of the game (can be the same as actualPrice if no discount is offered).
		.gamePlaytime (int, optional): The estimated playtime of the game in hours.
		.gameAge (int, optional): The minimum age requirement to play the game.
		.genre (String, required): The genre of the game.
		.website (String, required): The official website of the game (if any).
		.tags (String, required): A comma-separated list of tags or keywords describing the game.
		.aboutGame (String, optional): A brief description of the game.
		.releaseDate (LocalDate, optional): The release date of the game (formatted according to LocalDate class).
		.gameQuantity (int, optional): The number of copies available for purchase (only relevant if selling physical copies).
		.requirements (String, optional): System requirements to run the game.
		.file (MultipartFile, required): The image file representing the game.
		
		Response Format:

		.Upon successful publishing, the API returns a 200 OK response with a message: "published successfully".
		Any errors during the publishing process will result in an appropriate error code and message.
		
		Notes:

		.Authorization is required to access this endpoint.
		.All required parameters must be provided, otherwise the API will return an error.
		.The format of the gamePlatforms, tags, and requirements strings is expected to be comma-separated lists.
		.The LocalDate class should be used for the releaseDate parameter (check specific Java libraries for implementation details).
		
		
		API Documentation for publishAccessory
		Endpoint: /publishAccessory (POST)

		Description: This API endpoint allows authorized users (presumably administrators) to publish a new accessory product to the server.

		Request Parameters:

		.productName (String, optional): The name of the accessory product.
		.category (String, optional): The category of the accessory product.
		.description (String, optional): A brief description of the accessory product.
		.price (float, required): The price of the accessory product.
		.quantity (int, optional): The number of units available for purchase.
		.file (MultipartFile, required): The image file representing the accessory product.
		
		Response Format:

		.Upon successful publishing, the API returns a 200 OK response with a message: "published successfully".
		.Any errors during the publishing process will result in an appropriate error code and message.
		
		Notes:

		.Authorization is required to access this endpoint.
		.While all parameters are marked as optional, it's generally good practice to provide at least a product name and a description.
		.The format of the category string is expected to be a valid category existing in the system.
		
		
		API Documentation for complete-order/{orderNo}
		Endpoint: /complete-order/{orderNo} (POST)

		Description: This API endpoint allows authorized users (presumably administrators) to confirm the completion of an existing order by its order number.

		Path Variable:

		.orderNo (String, required): The unique order number of the order to be completed.
		
		Response Format:

		.Upon successful completion, the API returns a 200 OK response with a message: "completed".
		.Any errors during the completion process will result in an appropriate error code and message.
		
		Notes:

		Authorization is required to access this endpoint.
		The provided orderNo must be a valid and existing order number in the system
	   
		Base Controller Endpoints Documentation
	
	    Base Controller: This controller handles fundamental user account operations within the API.
	
	    Base Service: An external service responsible for executing the actual account creation and deletion actions.
	
	    **Endpoints:**
	
	    1. **Endpoint:** `/api/v1/base/createAccount` (POST)
	
		   - **Description:** Creates a new user account.
		   - **Request Body:**
			  - User object (details below).
		   - **Response:**
			  - 200 OK with message "created successfully" upon success.
			  - Appropriate error code and message if creation fails.

	    2. **Endpoint:** `/api/v1/base/deleteUser/{userId}` (DELETE)
	
		   - **Description:** Deletes an existing user account by its ID.
		   - **Path Variable:**
			  - `userId` (Long, required): The unique ID of the user to be deleted.
		   - **Response:**
			  - 200 OK with message "deleted successfully" upon success.
			  - 404 Not Found if the user ID doesn't exist.
			  - Appropriate error code and message if deletion fails.
	
	    **User Model:**
	
			- `first_name` (String): User's first name.
			- `last_name` (String): User's last name.
			- `password` (String): User's password.
			- `email` (String): User's email address.
			- `phones` (List<String>): A list of user's phone numbers.
	
	    
		  Search Controller Documentation (Search API)

	      This controller provides search functionalities and retrieves various data related to users, games, accessories, and orders within the GameBacket application. 
	
	    **Base URL:** `/api/v1/search`
	
	    **Endpoints:**
	
	    **1. Search Games by Name (GET /games):**
	
			- **Description:** Searches for games based on the provided query string.
			- **Request Parameter:**
				- `queryGames` (String, **required**): The user input for searching games (case-insensitive).
			- **Response:**
				- 200 OK with a list of `Games` objects that match the search query.
				- Appropriate error code and message on failure (e.g., no matching games found).
	
	    **2. Game Options (GET /game-options):**
	
			- **Description:** Retrieves a list of available game titles for autocomplete suggestions.
			- **Response:**
			- 200 OK with a list of `String` values representing game titles.

	    **3. Get Registered Users with Orders (GET /users-with-orders):**
	
			- **Description:** Retrieves a paginated list of registered users with their orders.
			- **Request Parameters:**
				- `pageNumber` (Integer, **optional**, default: 0): The page number of the results (zero-based indexing).
				- `pageSize` (Integer, **optional**, default: 6): The number of items per page.
			- **Response:**
				- 200 OK with a `PageResponse` object containing:
					- List of `Object[]` representing user and order details.
					- Current page number.
					- Total number of pages.
					- Total number of elements.
				- 400 Bad Request if `pageNumber` or `pageSize` is invalid (negative or zero).
				- Appropriate error code and message on internal server errors.
	
	    **4. Get Games with Total Orders (GET /games-with-orders):**
	
			- **Description:** Retrieves a paginated list of games with their total order count.
			- **Request Parameters:**
				- `pageNumber` (Integer, **optional**, default: 0): The page number of the results (zero-based indexing).
				- `pageSize` (Integer, **optional**, default: 6): The number of items per page.
		- **Response:**
			- 200 OK with a `PageResponse` object containing:
				- List of `Object[]` representing game and order details.
				- Current page number.
				- Total number of pages.
				- Total number of elements.
			- 400 Bad Request if `pageNumber` or `pageSize` is invalid (negative or zero).
			- Appropriate error code and message on internal server errors.

	    **5. Get Accessories with Orders (GET /accessories-with-orders):**
	
			- **Description:** Retrieves a paginated list of accessories with their total order count.
			- **Request Parameters:**
				- `pageNumber` (Integer, **optional**, default: 0): The page number of the results (zero-based indexing).
				- `pageSize` (Integer, **optional**, default: 6): The number of items per page.
			- **Response:**
				- 200 OK with a `PageResponse` object containing:
					- List of `Object[]` representing accessory and order details.
					- Current page number.
					- Total number of pages.
					- Total number of elements.
				- 400 Bad Request if `pageNumber` or `pageSize` is invalid (negative or zero).
				- Appropriate error code and message on internal server errors.
	
	    **6. Get Orders with Ordered Products (GET /orders-with-products):**
	
			- **Description:** Retrieves a paginated list of orders with their corresponding ordered products.
			- **Request Parameters:**
				- `pageNumber` (Integer, **optional**, default: 0): The page number of the results (zero-based indexing).
				- `pageSize` (Integer, **optional**, default: 6): The number of items per page.
		- **Response:**
			- 200 OK with a `PageResponse` object containing:
				- List of `Object[]` representing order and product details.
				- Current page number.
				- Total number of pages.
				- Total number of elements.
			- 400 Bad Request if `pageNumber` or `pageSize` is invalid (negative or zero).
			- Appropriate error code and message on internal server errors.

	    **7. Count Total Orders (GET /count-orders):**
	
			- **Description:** Retrieves the total number of orders in the system.
			- **Response:**
			  -200 OK with a `number of total orders`
	    - 
