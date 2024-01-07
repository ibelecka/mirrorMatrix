# Mirror Matrix Application

This application receives a 4x4 matrix of natural numbers and returns its mirrored look.

## Requirements

- Java 8 or higher
- Maven


## Installation and Usage

1. Clone the repository:
    
    git clone https://github.com/yourusername/mirror-matrix-app.git

2. Navigate to the project directory:

    cd mirror-matrix-app

3. Build the application:

    mvn clean install


4. Run the application:

    java -jar target/mirror-matrix-app.jar


## API Endpoint

- **POST /api/mirror**

    Receives a JSON object representing a 4x4 matrix of natural numbers and returns its mirrored matrix.

    Request body example:
    ```json
    {
        "matrix": [
            [1, 2, 3, 4],
            [5, 6, 7, 8],
            [9, 10, 11, 12],
            [13, 14, 15, 16]
        ]
    }

    Response example:
    ```json
    {
        "matrix": [
            [4, 3, 2, 1],
            [8, 7, 6, 5],
            [12, 11, 10, 9],
            [16, 15, 14, 13]
        ]
    }
    ```

## Testing

This project includes unit tests and integration tests to verify the functionality of the MatrixController and MatrixService. To run the tests, use the following command:

    mvn test
