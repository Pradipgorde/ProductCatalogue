# REST-API-StructureFor-ProductCatalogue

#Assignment: REST API for a Structured Product Catalogue

Objective: Implement RESTful API endpoints using Java for a product catalogue system, where products have a more complex, nested data structure. The system should use a database for storage, with a preference for MongoDB to take advantage of its ability to store nested documents.

#Task Description:

Product Entity Structure:
  
    Define a Product entity with the following attributes:
    
id: A unique identifier for the product.

name: The name of the product.

description: A text description of the product.

price: The price of the product.

categories: An array of categories (strings) the product belongs to.

attributes: An array of key-value pairs (objects) for additional attributes such as size, color, brand, etc.

availability: An object containing:
    inStock: A boolean indicating if the product is in stock.
    quantity: An integer representing the available quantity.

ratings: An array of objects representing user ratings, each with:
   userId: A unique identifier for the user who gave the rating.
   rating: A numerical rating value.
   comment: An optional text comment on the rating.

#API Functionality:
Create endpoints for adding, retrieving, updating, and deleting products.
Implement an endpoint for searching products with filters for name, category, and attributes.


# Explanation 

I have created five tables descrition below;

1.Customer Data : to maintain customer details.

2.ProductData : taken productId primaryType,  name, price, productCategory. data is fixed if we want update then its must be ovveride.

2.ProductSize : table having two colums productSize, ProductId. its maintain product size by ProductId;

3.ProductStock : table having three colums like productColor, productStock, ProductId.  productstock arranged over on color both are managed by ProductId.

4.ProductRating : table having four colums productId, UserId, RatingOutOff, Comments.







  
