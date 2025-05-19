alter table cart_item add constraint fk_cart_item_user foreign key (app_user_id) references app_user;
alter table cart_item add constraint fk_cart_item_product foreign key (product_id) references product;
alter table wishlist_item add constraint fk_wishlist_item_user foreign key (app_user_id) references app_user;
alter table wishlist_item add constraint fk_wishlist_item_product foreign key (product_id) references product;