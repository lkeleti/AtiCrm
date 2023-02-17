FROM node:19-alpine3.16 as BUILD
RUN mkdir -p /app
WORKDIR /app
COPY package.json /app
COPY package-lock.json /app
RUN npm install
RUN npm install -g @angular/cli@15
COPY . /app
RUN ng build --configuration production
FROM nginx:1.23-alpine
COPY --from=BUILD /app/dist/aticrm_angular /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 4200