CREATE TABLE product (

	id SERIAL PRIMARY KEY NOT NULL,
	name VARCHAR(100) NOT NULL,
	description VARCHAR(200) NOT NULL,
	price DECIMAL(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

INSERT INTO product VALUES(NEXTVAL('product_id_seq'),'course next level week','course react for development frontend with components','2000.00', now(), now());
INSERT INTO product VALUES(NEXTVAL('product_id_seq'),'course java with spring boot','spring framework for beginners with spring boot','3500.00', now(), now());
INSERT INTO product VALUES(NEXTVAL('product_id_seq'),'course immersion full cycle','development of modern and scalable applications with microservices.','4999.99', now(), now());
INSERT INTO product VALUES(NEXTVAL('product_id_seq'),'course devops essentials','discover the culture that is revolutionizing the it market.','2580.55', now(), now());
