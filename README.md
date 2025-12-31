# Spring Boot + Angular Shopping

A full-stack shopping app split into two projects:

- `shop/`: Spring Boot REST API with JWT auth and MySQL persistence.
- `shoppy/`: Angular UI for shopping and basic admin management.

The backend serves data and static images; the frontend consumes the API and manages auth via JWT stored in `localStorage`.

## Tech Stack

Backend
- Java + Spring Boot
- Spring Web, Spring Security, Spring Data JPA
- MySQL
- JWT authentication

Frontend
- Angular (standalone components, Angular Router)
- TypeScript, RxJS

## Project Structure

```
.
├── shop/                 # Spring Boot API
│   ├── src/main/java/... # Controllers, services, models, security
│   ├── src/main/resources
│   │   ├── application.properties
│   │   └── static/images # Uploaded product/category images
│   └── pom.xml
├── shoppy/               # Angular app
│   ├── src/app            # Components, routes, services
│   ├── src/styles.css
│   └── package.json
└── README.md
```

## Backend Overview (`shop/`)

Key layers:
- Controllers: REST endpoints for public API (`/api/*`) and admin API (`/admin/*`).
- Services + Impl: business logic and persistence orchestration.
- Repositories: Spring Data JPA repositories for CRUD queries.
- Models: JPA entities (`Category`, `Subcat`, `Childcat`, `Tag`, `Product`, `Order`, `OrderItem`, `AppUser`, `Role`).
- Security: JWT filter + user details service + security config.

Important classes:
- `com.coder.shop.controller.ApiController` handles public endpoints:
  - `GET /api/cats`, `/api/subcats`, `/api/childcats`, `/api/tags`, `/api/products`
  - `POST /api/login`, `/api/register`
  - `POST /api/orders` (requires JWT)
  - `POST /api/myOrders` (requires JWT)
- `com.coder.shop.controller.*Controller` under `/admin/*` for CRUD of categories, products, tags, roles, users, etc.
- `com.coder.shop.security.*` for JWT parsing and auth integration.
- `com.coder.shop.services.ImageService` saves uploads to `src/main/resources/static/images`.

Config:
- `server.port=3000`
- MySQL DB: `jdbc:mysql://localhost:3306/shoppy`
- JWT secret + expiration set in `application.properties`

Note: The JWT secret in `application.properties` is committed; for real use, move it to environment variables.

## Frontend Overview (`shoppy/`)

Routing is defined in `src/app/app.routes.ts` with pages for:
- Home, login, register, cart, order history
- Admin sections for categories, subcategories, child categories, tags, products, roles, users

API integration:
- `src/app/apiservice.ts` for public endpoints (`/api/*`)
- Feature services under `src/app/**/` for admin endpoints (`/admin/*`)

Auth:
- `src/app/auth.interceptor.ts` injects `Authorization: Bearer <token>` for API calls
- `src/app/login/login.ts` stores JWT in `localStorage`

Base URLs are defined in `src/app/utils/Vary.ts`:
- API base: `http://localhost:3000/api`
- Admin base: `http://localhost:3000/admin/...`

## Local Development

### 1) Database

Create a MySQL database named `shoppy`.

If needed, update credentials in:
- `shop/src/main/resources/application.properties`

### 2) Backend (Spring Boot)

From `shop/`:

```
./mvnw spring-boot:run
```

The API will run on `http://localhost:3000`.

### 3) Frontend (Angular)

From `shoppy/`:

```
npm install
npm start
```

The Angular dev server will run on `http://localhost:4200`.

## CORS / Port Notes

Backend controllers use `@CrossOrigin(origins = "http://localhost:4000")` while the Angular dev server defaults to `http://localhost:4200`. For local development, either:

- Change Angular to run on port 4000, or
- Update CORS origins in the backend to allow 4200.

## API Summary

Public endpoints (`/api/*`):
- `GET /api/cats`, `/api/subcats`, `/api/childcats`, `/api/tags`, `/api/products`
- `POST /api/login`
- `POST /api/register`
- `POST /api/orders` (JWT required)
- `POST /api/myOrders` (JWT required)

Admin endpoints (`/admin/*`):
- CRUD for categories, subcategories, child categories, tags, products, roles, users

Images:
- Uploaded images are available at `http://localhost:3000/images/<filename>`

## Testing

Backend: no explicit test setup found in this repo.
Frontend: Angular uses `ng test` (Vitest in `package.json`).

## Known Caveats

- `ProductImpl.add` throws `UnsupportedOperationException` and is not implemented; product creation may fail unless updated.
- JWT secret is committed to source; consider moving to environment variables.

## Suggested Next Steps

- Implement `ProductService.add` to allow product creation.
- Align CORS origins with your frontend port.
- Move DB credentials and JWT secret to environment variables.
