# Stage 1
FROM node:15.11.0-alpine as react-build
WORKDIR /app
COPY ./app/ .
RUN npm install
RUN npm run-script build


ENV URLS="[{url: 'https://api-staging.skyhive.ai/employees/api-docs.yaml',
name: 'Employees'},{url: 'https://api-staging.skyhive.ai/jobs/api-docs.yaml',
 name: 'Jobs'}, {url: 'https://api-staging.skyhive.ai/location/api-docs
 .yaml', name: 'Location'}]"
ENV BASE_NAME=""
ENV THEME_COLOR="#32329f"
ENV PAGE_TITLE="Skyhive"

CMD ["npm", "start"]
