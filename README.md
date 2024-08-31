# jooq-demo

## Installation

### Setup database

1. Setup Postgres: 
> psql -U postgres
> 
> CREATE ROLE "jooq-demo-user" WITH LOGIN PASSWORD 'jooq' SUPERUSER CREATEDB CREATEROLE;
>
> CREATE DATABASE "jooq-demo-db" OWNER "jooq-demo-user";
