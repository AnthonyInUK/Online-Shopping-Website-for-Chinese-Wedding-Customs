# 🎊 Online Shopping Website for Chinese Wedding Customs

A full-stack e-commerce platform specialized in Chinese wedding supplies and customs, built with Angular frontend and Spring Boot backend.

## 🌟 Features

### 🎭 Role-Based Shopping Experience
- **Groom Shopping Mode**: Tailored product categories for groom's wedding essentials
- **Bride Shopping Mode**: Specialized categories for bride's wedding preparations
- **Smart Product Filtering**: Automatic product suggestions based on selected role

### 💍 Product Categories
- **Groom Attire**: Traditional Chinese wedding suits and modern groom wear
- **Bridal Wear**: Wedding dresses and traditional Chinese bridal outfits
- **Wedding Jewelry**: Rings, bracelets, and traditional jewelry sets
- **Wedding Shoes**: Traditional and modern wedding footwear
- **Wedding Decorations**: Ceremony and reception decoration supplies
- **Wedding Candy**: Traditional Chinese wedding treats and gifts
- **Red Envelopes**: Traditional red envelopes for wedding gifts
- **Wedding Supplies**: Complete wedding ceremony and celebration items

### 🛒 E-commerce Features
- **Product Catalog**: Browse products by category and role
- **Shopping Cart**: Add/remove items with real-time price calculation
- **Secure Checkout**: Integrated Stripe payment processing
- **Order Tracking**: Complete order management system
- **User Authentication**: Secure login and registration

### 🎨 User Experience
- **Responsive Design**: Works on desktop, tablet, and mobile
- **Multilingual Support**: English and Chinese interface
- **Product Badges**: Clear indication of required vs optional items
- **Role Indicators**: Visual cues for target audience (Groom/Bride/Common)

## 🛠️ Technology Stack

### Frontend
- **Angular 10**: Modern web application framework
- **Bootstrap**: Responsive UI components
- **TypeScript**: Type-safe JavaScript development
- **RxJS**: Reactive programming for data flow

### Backend
- **Spring Boot**: Java-based REST API framework
- **Spring Data JPA**: Database abstraction layer
- **MySQL**: Relational database for product and order data
- **Spring Security**: Authentication and authorization

### Payment & Services
- **Stripe**: Secure payment processing
- **Email Support**: Order confirmation and tracking emails

## 🚀 Getting Started

### Prerequisites
- Java 11 or higher
- Node.js 14 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/AnthonyInUK/Online-Shopping-Website-for-Chinese-Wedding-Customs.git
   cd Online-Shopping-Website-for-Chinese-Wedding-Customs
   ```

2. **Database Setup**
   ```bash
   # Create MySQL database
   mysql -u root -p
   CREATE DATABASE full_stack_ecommerce;
   
   # Run database scripts
   cd 01-starter-files/db-scripts
   mysql -u root -p full_stack_ecommerce < 01-add-wedding-fields.sql
   mysql -u root -p full_stack_ecommerce < 02-update-categories-for-wedding.sql
   mysql -u root -p full_stack_ecommerce < 04-wedding-products-from-images.sql
   ```

3. **Backend Setup**
   ```bash
   cd backend/spring-boot-ecommerce
   
   # Copy and configure application properties
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   # Edit application.properties with your database credentials and Stripe API keys
   
   mvn clean install
   mvn spring-boot:run
   ```
   Backend will run on `http://localhost:8081`

   **Important**: Update the following in `application.properties`:
   - Database credentials (username, password)
   - Stripe API keys (get from [Stripe Dashboard](https://dashboard.stripe.com/apikeys))
   - Email configuration (optional)

4. **Frontend Setup**
   ```bash
   cd frontend/angular-ecommerce
   npm install
   NODE_OPTIONS=--openssl-legacy-provider npm start
   ```
   Frontend will run on `http://localhost:4200`

### 🎮 Usage

1. **Visit the website**: Open `http://localhost:4200`
2. **Select your role**: Choose "Groom" or "Bride" when prompted
3. **Browse products**: Explore role-specific product categories
4. **Add to cart**: Click "Add to cart" on desired items
5. **Checkout**: Complete your order with secure Stripe payment

### 🧪 Testing Payments

Use Stripe test card numbers:
- **Success**: `4242424242424242`
- **Declined**: `4000000000000002`
- **3D Secure**: `4000002500003155`

Use any future expiry date, CVC, and billing information.

## 🔒 Security & Configuration

### Sensitive Files (Not Included in Repository)

The following files contain sensitive information and are **NOT** included in this repository:

- `backend/spring-boot-ecommerce/src/main/resources/application.properties`
- `backend/spring-boot-ecommerce/src/main/resources/application-dev.properties`
- `backend/spring-boot-ecommerce/src/main/resources/application-prod.properties`
- `docker-compose.yml`

### Configuration Setup

1. **Copy example files**:
   ```bash
   cp backend/spring-boot-ecommerce/src/main/resources/application.properties.example backend/spring-boot-ecommerce/src/main/resources/application.properties
   cp docker-compose.example.yml docker-compose.yml
   ```

2. **Update configuration files** with your actual values:
   - Database credentials
   - Stripe API keys (from [Stripe Dashboard](https://dashboard.stripe.com/apikeys))
   - Email settings (optional)

3. **Never commit** these files to version control.

## 📁 Project Structure

```
├── backend/
│   └── spring-boot-ecommerce/          # Spring Boot REST API
│       ├── src/main/java/
│       │   └── com/luv2code/ecommerce/
│       │       ├── entity/             # JPA entities
│       │       ├── dao/                # Data access objects
│       │       └── controller/         # REST controllers
│       └── src/main/resources/
│           └── application.properties  # Database configuration
├── frontend/
│   └── angular-ecommerce/              # Angular frontend
│       ├── src/app/
│       │   ├── components/             # Angular components
│       │   ├── services/               # Angular services
│       │   └── common/                 # Shared models
│       └── src/assets/
│           └── images/wedding/         # Product images
├── 01-starter-files/
│   └── db-scripts/                     # Database migration scripts
└── README.md
```

## 🎨 Screenshots

### Role Selection
The application starts with a role selection modal where users choose whether they are shopping for the groom or bride.

### Groom Shopping Mode
When "Groom" is selected, the interface shows groom-specific product categories like Groom Attire, Wedding Jewelry, and Wedding Decorations.

### Bride Shopping Mode
When "Bride" is selected, the interface displays bride-focused categories including Bridal Wear, Wedding Shoes, Wedding Candy, and Red Envelopes.

### Checkout Process
Secure checkout with Stripe payment integration, showing order summary and payment form.

### Order Confirmation
Order confirmation page with tracking number and order details.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Anthony Jiang**
- GitHub: [@AnthonyInUK](https://github.com/AnthonyInUK)
- Email: anthonyxiamen@gmail.com

## 🙏 Acknowledgments

- Spring Boot community for excellent documentation
- Angular team for the amazing framework
- Stripe for seamless payment integration
- Chinese wedding traditions for inspiration

## 📞 Support

If you have any questions or need help with the project, please:
1. Open an issue on GitHub
2. Contact me at anthonyxiamen@gmail.com

---

**Happy Wedding Planning! 🎊💒**