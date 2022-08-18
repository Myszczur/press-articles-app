
# Press Article App

Simple REST API for managing press articles. The application allows you to save a new press article with the title, author and date of publication. Edit and delete an existing article.


## Features

- Full CRUD Press Article management
- JUnit tests
- Simple Error Handler
- Web Mvc Link Builder 


## Authors

- [@Myszczur](https://www.github.com/Myszczur)


## Tech Stack

**Tools:** IntellIj IDE, REST, Spring Boot, H2 database, Hateoas, Lombok



## API Reference

#### Get all articles sorted by publication date

```http
  GET /pressArticles
```

#### Get all articles with a keyword contained in the title or content

```http
  GET /pressArticles/{keyWord}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `keyWord`      | `string` | **Required**. Keyword of articles to fetch |

#### Delete article after the given id number

```http
  DELETE /pressArticle/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of article to delete |


#### Update article after the given id number

```http
  PUT /pressArticle/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of articles to update |
| `title`      | `string` | **Required**. title of article |
| `contents`      | `string` | **Required**. content of article |
| `publicationdate`      | `timestamp` | **Required**. publication date of article |
| `magazine`      | `string` | **Required**. publishing magazine of article |
| `authorfirstname`      | `string` | **Required**. author first name of article |
| `authorlastname`      | `string` | **Required**. author last name to update |
| `updated`      | `timestamp` | **Automatically completed**. date of editing the article |

#### Add new article

```http
  POST /pressArticle
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `title`      | `string` | **Required**. title of article |
| `contents`      | `string` | **Required**. content of article |
| `publicationdate`      | `timestamp` | **Required**. publication date of article |
| `magazine`      | `string` | **Required**. publishing magazine of article |
| `authorfirstname`      | `string` | **Required**. author first name of article |
| `authorlastname`      | `string` | **Required**. author last name to update |
| `created`      | `timestamp` | **Automatically completed**. date of adding to the database |

## Run Locally

Clone the project

```bash
  git clone https://github.com/Myszczur/press-articles-app
```

Go to the project directory

```bash
  cd press-articles-app
```

Build the Project

```bash
   mvn clean package

```

Start compiled JAR

```bash
  java -cp target/press-articles-app-1.0-SNAPSHOT.jar pl.urbanik.articlespressapp.App
```

