## Back-end

### Partie 1

Développer un back-end permettant la gestion de produits définis plus bas.


**/products**

Le back-end doit gérer les API suivantes : :white_check_mark:

Method | Path           | Description                    |
-------|----------------|--------------------------------|
GET    | /products      | retrieve all products          |
GET    | /products/{id} | retrieve one product by its ID |
POST   | /products      | Create a new product           |
PATCH  | /products      | update an existing product     |
DELETE | /products/{id} | remove an product by its ID    |


Un produit a les caractéristiques suivantes : 

``` typescript
class Product {
  id: number;
  code: string;
  name: string;
  description: string;
  image: string;
  category: string;
  price: number;
  quantity: number;
  internalReference: string;
  shellId: number;
  inventoryStatus: "INSTOCK" | "LOWSTOCK" | "OUTOFSTOCK";
  rating: number;
  createdAt: number;
  updatedAt: number;
}
```

Le back-end créé doit pouvoir gérer les produits dans une base de données SQL/NoSQL ou dans un fichier json. :white_check_mark:

### Partie 2

- Imposer à l'utilisateur de se connecter pour accéder à l'API.
  La connexion doit être gérée en utilisant un token JWT.  
  Deux routes devront être créées :
  * [POST] /account -> Permet de créer un nouveau compte pour un utilisateur avec les informations fournies par la requête.   
    Payload attendu : 
    ```
    {
      username: string,
      firstname: string,
      email: string,
      password: string
    }
    ```
  * [POST] /token -> Permet de se connecter à l'application.  
    Payload attendu :  
    ```
    {
      email: string,
      password: string
    }
    ```
    Une vérification devra être effectuée parmi tout les utilisateurs de l'application afin de connecter celui qui correspond aux infos fournies. Un token JWT sera renvoyé en retour de la reqûete. :white_check_mark:
- Faire en sorte que seul l'utilisateur ayant le mail "admin@admin.com" puisse ajouter, modifier ou supprimer des produits. Une solution simple et générique devra être utilisée. Il n'est pas nécessaire de mettre en place une gestion des accès basée sur les rôles. :white_check_mark:
- Ajouter la possibilité pour un utilisateur de gérer un panier d'achat pouvant contenir des produits. :white_check_mark:
- Ajouter la possibilité pour un utilisateur de gérer une liste d'envie pouvant contenir des produits. :white_check_mark:

## Bonus

Vous pouvez ajouter des tests Postman ou Swagger pour valider votre API :white_check_mark: