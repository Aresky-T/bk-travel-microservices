FROM node:20.12.0-alpine as build
# Setting the work directory
WORKDIR /app
# Copy package*.json file to app folder
COPY package.json .
COPY package-lock.json .
# Install dependencies
RUN npm install
COPY . .
# EXPOSE 3000
# ENTRYPOINT [ "npm", "start" ]
# Start the application
# build /app
RUN npm run build
# Set up nginx server
FROM nginx:alpine as production
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]
LABEL author="Aresky"