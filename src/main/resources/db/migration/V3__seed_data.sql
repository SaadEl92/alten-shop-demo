insert into app_user (email,firstname,password,username) 
    values ('admin@admin.com','admin firsname','$2a$10$4LwdAGP82iUu923xiTBDJ.W3qKXbQ9jqo/oZtwEn86Xg3XTOBIxXS','admin');

insert into product (category,code,created_at,description,image,internal_reference,inventory_status,name,price,quantity,rating,shell_id,updated_at)
    values ('TV & Monitors','f230fh0g3',now(),'OLED 4K','image.jpg','REF-123-456','INSTOCK','Samsung TV',1200.99,3,4,15,now());
insert into product (category,code,created_at,description,image,internal_reference,inventory_status,name,price,quantity,rating,shell_id,updated_at)
    values ('Audio','a1b2c3d4',now(),'Noise cancelling headphones','headphones.jpg','REF-789-012','LOWSTOCK','Sony WH-1000XM4',299.99,10,5,30,now());
