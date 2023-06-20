
---- --- ---         Báo cáo đồ án          --- --- ----

-- Đề tài: Trang web bán giày 
-- Thành viên nhóm: 
    --                21521230 - Ngô Thùy Yến Nhi
    --                21521990 - Nguyễn Dương
    --                21522211 - Phạm Duy Khánh
    --                21522351 - Nguyễn Ngọc Hà My
    
-- Database của đồ án:
-- Lưu ý: DBMS sử dụng là Oracle

CREATE TABLE BRAND (BRANDID NUMBER(10,0), BRANDNAME VARCHAR2(50));

CREATE TABLE CART (CARTID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER NOCYCLE NOKEEP NOSCALE, TOTAL NUMBER(19,2), USER_ID NUMBER(19,0));

CREATE TABLE CART_DETAILS (CART_DETAILSID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER NOCYCLE NOKEEP NOSCALE, QUANTITY NUMBER(10,0), TOTAL NUMBER(19,2), CARTID NUMBER(19,0), PRODUCT_ID NUMBER(19,0));

CREATE TABLE CATEGORY (CATEGORYID NUMBER(10,0), CATEGORYNAME VARCHAR2(50));

CREATE TABLE INVOICE (INVOICEID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER NOCYCLE NOKEEP NOSCALE, ORDER_DATE TIMESTAMP (6), SHIP_ADDRESS VARCHAR2(255 CHAR), STATUS VARCHAR2(255 CHAR), TOTAL_PRICE NUMBER(19,2), USER_ID NUMBER(19,0));

CREATE TABLE INVOICE_DETAILS (INVOICE_DETAILS_ID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER NOCYCLE NOKEEP NOSCALE, DISCOUNT NUMBER(19,2), PRICE NUMBER(19,2), QUANTITY NUMBER(10,0), INVOICEID NUMBER(19,0), PRODUCT_ID NUMBER(19,0));

CREATE TABLE PRODUCTREVIEW (REVIEWID NUMBER, USERID NUMBER(19,0), PRODUCTID NUMBER(19,0), RATING NUMBER(1,0), COMMENTS VARCHAR2(500), REVIEWDATE DATE);

CREATE TABLE PRODUCTS (ID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER NOCYCLE NOKEEP NOSCALE, BRAND_NAME VARCHAR2(255 CHAR), CATEGORY VARCHAR2(255 CHAR), DESCRIPTION VARCHAR2(255 CHAR), DESIGNER VARCHAR2(255 CHAR), IMG_URL VARCHAR2(255 CHAR), PRODUCT_NAME VARCHAR2(255 CHAR), PRODUCT_PRICE NUMBER(19,2), QUANTITY NUMBER(10,0));

CREATE TABLE ROLES (ID NUMBER(10,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER NOCYCLE NOKEEP NOSCALE, NAME VARCHAR2(20 CHAR));

CREATE TABLE USER_ROLES (USER_ID NUMBER(19,0), ROLE_ID NUMBER(10,0));

CREATE TABLE USERS (ID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER NOCYCLE NOKEEP NOSCALE, ADDRESS VARCHAR2(255 CHAR), BIRTH VARCHAR2(255 CHAR), EMAIL VARCHAR2(50 CHAR), GENDER VARCHAR2(255 CHAR), PASSWORD VARCHAR2(120 CHAR), PHONE VARCHAR2(255 CHAR), ROLE VARCHAR2(255 CHAR), USERNAME VARCHAR2(20 CHAR));

SET DEFINE OFF;

CREATE UNIQUE INDEX SYS_C008274 ON BRAND (BRANDID);

CREATE UNIQUE INDEX SYS_C008275 ON BRAND (BRANDNAME);

CREATE UNIQUE INDEX SYS_C008357 ON CART (CARTID);

CREATE UNIQUE INDEX SYS_C008321 ON CART_DETAILS (CART_DETAILSID);

CREATE UNIQUE INDEX SYS_C008277 ON CATEGORY (CATEGORYID);

CREATE UNIQUE INDEX SYS_C008278 ON CATEGORY (CATEGORYNAME);

CREATE UNIQUE INDEX SYS_C008324 ON INVOICE (INVOICEID);

CREATE UNIQUE INDEX SYS_C008327 ON INVOICE_DETAILS (INVOICE_DETAILS_ID);

CREATE UNIQUE INDEX SYS_C008270 ON PRODUCTREVIEW (REVIEWID);

CREATE UNIQUE INDEX SYS_C008330 ON PRODUCTS (ID);

CREATE UNIQUE INDEX SYS_C007868 ON ROLES (ID);

CREATE UNIQUE INDEX SYS_C008360 ON USER_ROLES (USER_ID, ROLE_ID);

CREATE UNIQUE INDEX SYS_C008362 ON USERS (ID);

CREATE UNIQUE INDEX UKR43AF9AP4EDM43MMTQ01ODDJ6 ON USERS (USERNAME);

CREATE UNIQUE INDEX UK6DOTKOTT2KJSP8VW4D0M25FB7 ON USERS (EMAIL);
--------------------------------------------------------
--  Constraints for Table BRAND
--------------------------------------------------------

ALTER TABLE BRAND MODIFY (BRANDNAME NOT NULL ENABLE);
ALTER TABLE BRAND ADD PRIMARY KEY (BRANDID) USING INDEX ENABLE;
ALTER TABLE BRAND ADD UNIQUE (BRANDNAME) USING INDEX ENABLE;

--------------------------------------------------------
--  Constraints for Table CART
--------------------------------------------------------

ALTER TABLE CART MODIFY (CARTID NOT NULL ENABLE);
ALTER TABLE CART ADD PRIMARY KEY (CARTID) USING INDEX ENABLE;

--------------------------------------------------------
--  Constraints for Table CART_DETAILS
--------------------------------------------------------

ALTER TABLE CART_DETAILS MODIFY (CART_DETAILSID NOT NULL ENABLE);
ALTER TABLE CART_DETAILS MODIFY (QUANTITY NOT NULL ENABLE);
ALTER TABLE CART_DETAILS ADD PRIMARY KEY (CART_DETAILSID) USING INDEX ENABLE;

--------------------------------------------------------
--  Constraints for Table CATEGORY
--------------------------------------------------------

ALTER TABLE CATEGORY MODIFY (CATEGORYNAME NOT NULL ENABLE);
ALTER TABLE CATEGORY ADD PRIMARY KEY (CATEGORYID) USING INDEX ENABLE;
ALTER TABLE CATEGORY ADD UNIQUE (CATEGORYNAME) USING INDEX ENABLE;

--------------------------------------------------------
--  Constraints for Table INVOICE
--------------------------------------------------------

ALTER TABLE INVOICE MODIFY (INVOICEID NOT NULL ENABLE);
ALTER TABLE INVOICE MODIFY (USER_ID NOT NULL ENABLE);
ALTER TABLE INVOICE ADD PRIMARY KEY (INVOICEID) USING INDEX ENABLE;

--------------------------------------------------------
--  Constraints for Table INVOICE_DETAILS
--------------------------------------------------------

ALTER TABLE INVOICE_DETAILS MODIFY (INVOICE_DETAILS_ID NOT NULL ENABLE);
ALTER TABLE INVOICE_DETAILS MODIFY (QUANTITY NOT NULL ENABLE);
ALTER TABLE INVOICE_DETAILS ADD PRIMARY KEY (INVOICE_DETAILS_ID) USING INDEX ENABLE;

--------------------------------------------------------
--  Constraints for Table PRODUCTREVIEW
--------------------------------------------------------

ALTER TABLE PRODUCTREVIEW MODIFY (USERID NOT NULL ENABLE);
ALTER TABLE PRODUCTREVIEW MODIFY (PRODUCTID NOT NULL ENABLE);
ALTER TABLE PRODUCTREVIEW MODIFY (RATING NOT NULL ENABLE);
ALTER TABLE PRODUCTREVIEW MODIFY (REVIEWDATE NOT NULL ENABLE);
ALTER TABLE PRODUCTREVIEW ADD PRIMARY KEY (REVIEWID) USING INDEX ENABLE;

--------------------------------------------------------
--  Constraints for Table PRODUCTS
--------------------------------------------------------

ALTER TABLE PRODUCTS MODIFY (ID NOT NULL ENABLE);
ALTER TABLE PRODUCTS MODIFY (QUANTITY NOT NULL ENABLE);
ALTER TABLE PRODUCTS ADD PRIMARY KEY (ID) USING INDEX ENABLE;

--------------------------------------------------------
--  Constraints for Table ROLES
--------------------------------------------------------

ALTER TABLE ROLES MODIFY (ID NOT NULL ENABLE);
ALTER TABLE ROLES ADD PRIMARY KEY (ID) USING INDEX ENABLE;

--------------------------------------------------------
--  Constraints for Table USER_ROLES
--------------------------------------------------------

ALTER TABLE USER_ROLES MODIFY (USER_ID NOT NULL ENABLE);
ALTER TABLE USER_ROLES MODIFY (ROLE_ID NOT NULL ENABLE);
ALTER TABLE USER_ROLES ADD PRIMARY KEY (USER_ID, ROLE_ID) USING INDEX ENABLE;

--------------------------------------------------------
--  Constraints for Table USERS
--------------------------------------------------------

ALTER TABLE USERS MODIFY (ID NOT NULL ENABLE);
ALTER TABLE USERS ADD PRIMARY KEY (ID) USING INDEX ENABLE;
ALTER TABLE USERS ADD CONSTRAINT UKR43AF9AP4EDM43MMTQ01ODDJ6 UNIQUE (USERNAME) USING INDEX ENABLE;
ALTER TABLE USERS ADD CONSTRAINT UK6DOTKOTT2KJSP8VW4D0M25FB7 UNIQUE (EMAIL) USING INDEX ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table CART
--------------------------------------------------------

ALTER TABLE CART ADD CONSTRAINT FKG5UHI8VPSUY0LGLOXK2H4W5O6 FOREIGN KEY (USER_ID) REFERENCES USERS (ID) ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table CART_DETAILS
--------------------------------------------------------

ALTER TABLE CART_DETAILS ADD CONSTRAINT FKCN1Y9ER648NIQYW2O6XTS7260 FOREIGN KEY (CARTID) REFERENCES CART (CARTID) ENABLE;
ALTER TABLE CART_DETAILS ADD CONSTRAINT FK9RLIC3AYNL3G75JVEDKX84LHV FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS (ID) ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table INVOICE
--------------------------------------------------------

ALTER TABLE INVOICE ADD CONSTRAINT FKC8JOTSKR93810VGN75QLBUSW2 FOREIGN KEY (USER_ID) REFERENCES USERS (ID) ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table INVOICE_DETAILS
--------------------------------------------------------

ALTER TABLE INVOICE_DETAILS ADD CONSTRAINT FKJ26286QV1HDS18BLKJ46TJIY0 FOREIGN KEY (INVOICEID) REFERENCES INVOICE (INVOICEID) ENABLE;
ALTER TABLE INVOICE_DETAILS ADD CONSTRAINT FKCHHYDD0D280RUIG3HMARS76WA FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS (ID) ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table USER_ROLES
--------------------------------------------------------

ALTER TABLE USER_ROLES ADD CONSTRAINT FKH8CIRAMU9CC9Q3QCQIV4UE8A6 FOREIGN KEY (ROLE_ID) REFERENCES ROLES (ID) ENABLE;
ALTER TABLE USER_ROLES ADD CONSTRAINT FKHFH9DX7W3UBF1CO1VDEV94G3F FOREIGN KEY (USER_ID) REFERENCES USERS (ID);

-- Insert dữ liệu sản phẩm vào bảng Products
INSERT INTO PRODUCTS (BRAND_NAME, CATEGORY, DESCRIPTION, DESIGNER, IMG_URL, PRODUCT_NAME, PRODUCT_PRICE, QUANTITY) VALUES (
'Air Jordan',  
'basketball',  
'Nike Air Jordan 1 Retro High OG Shadow 2018 là phiên bản tái phát hành từ bản gốc năm 1985. Đôi giày có phần trên là chất liệu da màu đen pha xám, với đế trắng và mặt đế đen. Đặc biệt, trên đôi giày có logo OG Nike Air.', 
'Peter Moore', 
'https://image.goat.com/750/attachments/product_template_pictures/images/011/119/994/original/218099_00.png.png',  
'Air Jordan 1 Retro High OG Shadow 2018', 2000000, 20); 
/
INSERT INTO PRODUCTS (BRAND_NAME, CATEGORY, DESCRIPTION, DESIGNER, IMG_URL, PRODUCT_NAME, PRODUCT_PRICE, QUANTITY) VALUES (
'Air Jordan',  
'basketball',  
'Nike Air Jordan 1 Retro High OG Shadow 2018 là phiên bản tái phát hành từ bản gốc năm 1985. Đôi giày có phần trên là chất liệu da màu đen pha xám, với đế trắng và mặt đế đen.',
'Tinker Hatfield', 
'https://image.goat.com/750/attachments/product_template_pictures/images/008/654/900/original/52015_00.png.png',  
'Air Jordan 4 Retro OG GS Bred 2019',  
3000000,  
18); 
/
INSERT INTO PRODUCTS (BRAND_NAME, CATEGORY, DESCRIPTION, DESIGNER, IMG_URL, PRODUCT_NAME, PRODUCT_PRICE, QUANTITY) VALUES (
'Air Jordan',  
'basketball',  
'Air Jordan 11 Retro Space Jam phiên bản retro năm 2016 này đã trở thành một trong những lần ra mắt thành công nhất của Nike tính đến thời điểm đó.',
'Tinker Hatfield', 
'https://image.goat.com/750/attachments/product_template_pictures/images/008/654/900/original/52015_00.png.png',  
'Air Jordan 11 Retro Space Jam 2016', 2500000,16); 
/
INSERT INTO PRODUCTS (BRAND_NAME, CATEGORY, DESCRIPTION, DESIGNER, IMG_URL, PRODUCT_NAME, PRODUCT_PRICE, QUANTITY) VALUES (
'Air Jordan',  
'basketball',  
'Năm 1996 là năm mà đội Chicago Bulls kết thúc mùa giải thường lệ với kỷ lục 72 chiến thắng, Michael đã mang đôi giày Jordan 11 trong chuỗi trận thắng đó, và phiên bản phát hành vào năm 2017 nhằm tôn vinh đội hình 96 bất khả chiến bại.',
'Tinker Hatfield', 
'https://image.goat.com/750/attachments/product_template_pictures/images/008/870/353/original/235806_00.png.png',
'Air Jordan 11 Retro Win Like 96',  
3500000,  
16); 
/
INSERT INTO PRODUCTS (BRAND_NAME, CATEGORY, DESCRIPTION, DESIGNER, IMG_URL, PRODUCT_NAME, PRODUCT_PRICE, QUANTITY) VALUES (
'Air Jordan',  
'basketball',  
'Air Jordan 11 Retro Legend Blue 2014 lấy cảm hứng từ Jordan 11 Columbia năm 1996 được Jordan mặc lần đầu trong trận đấu NBA All-Star năm 1996.',
'Tinker Hatfield', 
'https://image.goat.com/750/attachments/product_template_pictures/images/010/223/048/original/13607_00.png.png',
'Air Jordan 11 Retro Legend Blue 2014',  
4500000,  
16);
/
INSERT INTO PRODUCTS (BRAND_NAME, CATEGORY, DESCRIPTION, DESIGNER, IMG_URL, PRODUCT_NAME, PRODUCT_PRICE, QUANTITY) VALUES (
'Nike',  
'lifestyle',  
'Cuộc thi On Air năm 2018, người chiến thắng Gwang Shin đã ra mắt giày thể thao Air Max 97 On Air: Neon để bày tỏ sự ngưỡng mộ đối với thành phố Seoul của mình.',
'Gwang Shin', 
'https://image.goat.com/750/attachments/product_template_pictures/images/020/627/570/original/491891_00.png.png',
'Air Max 97 On Air: Neon Seoul',  
1500000,  
15); 
/
INSERT INTO PRODUCTS (BRAND_NAME, CATEGORY, DESCRIPTION, DESIGNER, IMG_URL, PRODUCT_NAME, PRODUCT_PRICE, QUANTITY) VALUES (
'Nike',  
'lifestyle',  
'Off-White x Air Max 90 ‘Black’ mang đến sự pha trộn độc đáo giữa các chất liệu kết hợp phần đế bằng ripstop phủ lớp nubuck cùng thiết kế da lộn',
'Jerry Lorenz', 
'https://image.goat.com/750/attachments/product_template_pictures/images/012/750/761/original/351623_00.png.png',
'OFF-WHITE x Air Max 90 Black',   --bit
7500000,  
15); 
/
INSERT INTO PRODUCTS (BRAND_NAME, CATEGORY, DESCRIPTION, DESIGNER, IMG_URL, PRODUCT_NAME, PRODUCT_PRICE, QUANTITY) VALUES (
'Adidas',  
'lifestyle',  
'Đôi giày thể thao này có phần trên màu trắng và xám trung tính,  dòng chữ SPLY-350 màu đỏ ở mặt sau. Giày cũng đi kèm với một miếng dán ở gót chân, lớp lót bên trong tông màu xanh lam.',
'Kanye West', 
'https://image.goat.com/750/attachments/product_template_pictures/images/021/147/972/original/504187_00.png.png',
'Yeezy Boost 700 V2 Vanta',   
5500000,  
15); 
/
INSERT INTO PRODUCTS (BRAND_NAME, CATEGORY, DESCRIPTION, DESIGNER, IMG_URL, PRODUCT_NAME, PRODUCT_PRICE, QUANTITY) VALUES (
'Adidas',  
'lifestyle',  
'Đôi giày Yeezy Boost 350 V2 Beluga 2.0 có sọc xám mờ ở hai bên thay vì sọc cam sáng như trên phiên bản ban đầu của giày thể thao Beluga. Ngoài ra, nó còn được trang bị tab kéo gót với đường khâu màu cam và chữ SPLY-350 màu cam ngược ở hai bên.',
'Kanye West', 
'https://image.goat.com/750/attachments/product_template_pictures/images/008/654/534/original/152982_00.png.png',
'Yeezy Boost 350 V2 Beluga 2.0',   
4599000,  
16); 
/
INSERT INTO PRODUCTS (BRAND_NAME, CATEGORY, DESCRIPTION, DESIGNER, IMG_URL, PRODUCT_NAME, PRODUCT_PRICE, QUANTITY) VALUES (
'Adidas',  
'lifestyle',  
'Đôi giày Yeezy Boost 350 V2 Sesame mang một bảng màu tinh tế phối hợp hoàn hảo với thiết kế tối giản của đôi giày. Phần trên Primeknit thoáng khí giữ nguyên tab gót và chi tiết may trung tâm đặc trưng, nhưng không còn chữ SPLY-350 được phản chiếu',
'Kanye West', 
'https://image.goat.com/750/attachments/product_template_pictures/images/014/507/851/original/195483_00.png.png',
'Yeezy Boost 350 V2 Sesame',   
5600000,  
16); 
/
INSERT INTO PRODUCTS (BRAND_NAME, CATEGORY, DESCRIPTION, DESIGNER, IMG_URL, PRODUCT_NAME, PRODUCT_PRICE, QUANTITY) VALUES (
'Nike',  
'running',  
'Air Max 97 Triple White có phần trên bằng da trắng và lưới với điểm nhấn màu Wolf Grey. Ra mắt vào tháng 8 năm 2017, đôi giày là một trong những thiết kế của bộ sưu tập Air Max.',
'Christian Tresser', 
'https://image.goat.com/750/attachments/product_template_pictures/images/021/321/832/original/503571_00.png.png',
'Air Max 97 Triple White',   
5600000,  
16);  
/
INSERT INTO PRODUCTS (BRAND_NAME, CATEGORY, DESCRIPTION, DESIGNER, IMG_URL, PRODUCT_NAME, PRODUCT_PRICE, QUANTITY) VALUES (
'Nike',  
'running',  
'Ra mắt vào tháng 3 năm 2018, Air Max 270 White lấy cảm hứng từ cả Air Max 180 và Air Max 93. Phần trên bằng lưới trắng được nhấn nhá bằng những điểm màu xám trên vòng kéo gót cùng logo Swoosh trên đầu ngón chân và bên hông',
'Christian Tresser', 
'https://image.goat.com/750/attachments/product_template_pictures/images/010/634/133/original/303217_00.png.png',
'Air Max 270',   
1999999,  
16);
/
INSERT INTO PRODUCTS (BRAND_NAME, CATEGORY, DESCRIPTION, DESIGNER, IMG_URL, PRODUCT_NAME, PRODUCT_PRICE, QUANTITY) VALUES (
'Adidas',  
'running',  
'Thiết kế được trang bị phần trên bằng chất liệu Primeknit đặc trưng của adidas, đôi giày phong cách này có màu vàng sáng năng động, chạy dọc từ dây buộc giày, vòng kéo gót đến phần đế cao su với công nghệ Boost.',
'Kanye West', 
'https://image.goat.com/750/attachments/product_template_pictures/images/016/928/118/original/155573_00.png.png',
'Yeezy Boost 350 V2 Semi Frozen Yellow',   
7800000,  
16); 
/
INSERT INTO PRODUCTS (BRAND_NAME, CATEGORY, DESCRIPTION, DESIGNER, IMG_URL, PRODUCT_NAME, PRODUCT_PRICE, QUANTITY) VALUES (
'Converse',  
'lifestyle',  
'Phiên bản Comme des Garçons x Chuck Taylor All Star Hi này có phần trên bằng vải màu kem nhạt, logo tim CDG màu đỏ ở các bên hông, dải đối lập màu đen trên gót, đầu giày màu trắng và đế giữa bằng cao su.',
'Marquis Mills', 
'https://image.goat.com/750/attachments/product_template_pictures/images/015/298/767/original/77243_00.png.png',
'Comme des Garçons x Chuck Taylor All Star Hi Milk',   
5600000, 
16); 
/
INSERT INTO PRODUCTS (BRAND_NAME, CATEGORY, DESCRIPTION, DESIGNER, IMG_URL, PRODUCT_NAME, PRODUCT_PRICE, QUANTITY) VALUES (
'Converse',  
'lifestyle',  
'Phiên bản Artist Series của Converse Chuck 70, với phần trên bằng vải màu kem nhạt được in họa tiết gốc từ Wyatt Navarro. Phần viền đặc trưng của dáng giày được tăng cao và được trang trí với các dải tương phản màu xanh và cam.',
'Marquis Mills', 
'https://image.goat.com/750/attachments/product_template_pictures/images/018/552/840/original/476518_00.png.png',
'Tyler, The Creator x Foot Locker x Chuck 70 Artist Series',   
1000000, 20); 
/
-- Insert dữ liệu vào bảng Brands

Insert into BRAND (BRANDID,BRANDNAME) values (9,'Air Jordan ');
Insert into BRAND (BRANDID,BRANDNAME) values (10,'Converse');
Insert into BRAND (BRANDID,BRANDNAME) values (2,'adidas');
Insert into BRAND (BRANDID,BRANDNAME) values (4,'adlv');
Insert into BRAND (BRANDID,BRANDNAME) values (5,'balenciaga');
Insert into BRAND (BRANDID,BRANDNAME) values (7,'drew');
Insert into BRAND (BRANDID,BRANDNAME) values (8,'essential');
Insert into BRAND (BRANDID,BRANDNAME) values (6,'mlb');
Insert into BRAND (BRANDID,BRANDNAME) values (1,'nike');
Insert into BRAND (BRANDID,BRANDNAME) values (3,'puma');

-- Insert dữ liệu vào bảng Category

Insert into CATEGORY (CATEGORYID,CATEGORYNAME) values (3,'basketball');
Insert into CATEGORY (CATEGORYID,CATEGORYNAME) values (1,'lifestyle');
Insert into CATEGORY (CATEGORYID,CATEGORYNAME) values (2,'running');

-- -- Insert dữ liệu vào bảng Roles
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
commit;

--- ======================================================================== ---

    --- ----                    Trigger                     ---- ---
-- Trigger Cập nhật số lượng sản phẩm khi có thay đổi trong chi tiết hóa đơn.
CREATE OR REPLACE TRIGGER trg_update_product_quantity
AFTER INSERT OR UPDATE OR DELETE ON INVOICE_DETAILS
FOR EACH ROW
DECLARE
    --pragma autonomous_transaction;
    v_product_id INVOICE_DETAILS.PRODUCT_ID%TYPE;
    v_quantity INVOICE_DETAILS.QUANTITY%TYPE;
BEGIN
    -- Lấy thông tin sản phẩm và số lượng từ chi tiết hóa đơn
    v_product_id := :NEW.PRODUCT_ID;
    v_quantity := :NEW.QUANTITY;

    IF INSERTING THEN
        -- Cập nhật số lượng sản phẩm khi có thêm dữ liệu vào chi tiết hóa đơn
        UPDATE PRODUCTS
        SET PRODUCTS.QUANTITY = PRODUCTS.QUANTITY - v_quantity
        WHERE PRODUCTS.ID = v_product_id;
    ELSIF UPDATING THEN
        -- Lấy thông tin số lượng sản phẩm trước khi cập nhật
        DECLARE
            v_old_quantity INVOICE_DETAILS.QUANTITY%TYPE;
        BEGIN
            SELECT INVOICE_DETAILS.QUANTITY INTO v_old_quantity
            FROM INVOICE_DETAILS
            WHERE INVOICEID = :NEW.INVOICEID AND PRODUCT_ID = v_product_id;

            -- Cập nhật số lượng sản phẩm khi có sự thay đổi trong chi tiết hóa đơn
            UPDATE PRODUCTS
            SET PRODUCTS.QUANTITY = PRODUCTS.QUANTITY + v_old_quantity - v_quantity
            WHERE PRODUCTS.ID = v_product_id;
        END;
    ELSIF DELETING THEN
        -- Lấy thông tin số lượng sản phẩm từ chi tiết hóa đơn
        DECLARE
            v_old_quantity INVOICE_DETAILS.QUANTITY%TYPE;
        BEGIN
            SELECT INVOICE_DETAILS.QUANTITY INTO v_old_quantity
            FROM INVOICE_DETAILS
            WHERE INVOICEID = :OLD.INVOICEID AND PRODUCT_ID = v_product_id;

            -- Cập nhật số lượng sản phẩm khi có dữ liệu bị xóa khỏi chi tiết hóa đơn
            UPDATE PRODUCTS
            SET QUANTITY = QUANTITY + v_old_quantity
            WHERE PRODUCTS.ID = v_product_id;
            
        END;
    END IF;
   --commit;
END;
/

-- Kiểm tra số lượng sản phẩm trong kho khi tạo hóa đơn. 
CREATE OR REPLACE TRIGGER check_product_quantity
BEFORE INSERT ON INVOICE_DETAILS
FOR EACH ROW
DECLARE
    v_available_quantity NUMBER;
BEGIN
    -- Lấy số lượng sản phẩm có trong kho
    SELECT QUANTITY INTO v_available_quantity
    FROM PRODUCTS
    WHERE ID = :NEW.PRODUCT_ID;

    -- Kiểm tra nếu số lượng yêu cầu trong hóa đơn lớn hơn số lượng có trong kho
    IF :NEW.QUANTITY > v_available_quantity THEN
        -- Hiển thị thông báo lỗi
        RAISE_APPLICATION_ERROR(-20001, 'Số lượng sản phẩm không đủ trong kho: ' || :NEW.PRODUCT_ID);
        --ROLLBACK;
    END IF;
END;
/
-- Ràng buộc brand name
CREATE OR REPLACE TRIGGER trg_validate_brandname
BEFORE INSERT OR UPDATE ON PRODUCTS
FOR EACH ROW
DECLARE
    brand_exists NUMBER;
BEGIN
    -- Kiểm tra xem brandname (không phân biệt hoa thường) có tồn tại trong bảng BRAND không
    SELECT COUNT(*) INTO brand_exists FROM BRAND WHERE UPPER(BRANDNAME) = UPPER(:NEW.BRAND_NAME);
    IF brand_exists = 0 THEN
        -- Nếu không tồn tại, ném exception và báo lỗi
        RAISE_APPLICATION_ERROR(-20001, 'Brandname không hợp lệ: ' || :NEW.BRAND_NAME);
    END IF;
END;
/

-- Ràng buộc Category
CREATE OR REPLACE TRIGGER trg_validate_category
BEFORE INSERT OR UPDATE ON PRODUCTS
FOR EACH ROW
DECLARE
    category_exists NUMBER;
BEGIN
    -- Kiểm tra xem Category có tồn tại trong bảng CATEGORY không
    SELECT COUNT(*) INTO category_exists FROM CATEGORY WHERE CATEGORYNAME = :NEW.Category;
    IF category_exists = 0 THEN
        -- Nếu không tồn tại, ném exception và báo lỗi
        RAISE_APPLICATION_ERROR(-20002, 'Category không hợp lệ: ' || :NEW.Category);
    END IF;
END;

/
-- Tính tiền hóa đơn 
CREATE OR REPLACE TRIGGER Trg_INVOICE_VALUE
AFTER INSERT OR UPDATE OR DELETE ON INVOICE_DETAILS
FOR EACH ROW
DECLARE
    pragma autonomous_transaction;
    invoice_value NUMBER;
BEGIN
    SELECT SUM(QUANTITY * PRICE) INTO invoice_value
    FROM INVOICE_DETAILS
    WHERE INVOICEID = :NEW.INVOICEID;

    UPDATE INVOICE
    SET TOTAL_PRICE = invoice_value
    WHERE INVOICEID = :NEW.INVOICEID;
    
    COMMIT;
END;
/
-- Kiểm tra ngày sinh khách hàng
CREATE OR REPLACE TRIGGER Trg_birth_user
BEFORE INSERT OR UPDATE ON USERS
FOR EACH ROW
DECLARE
    birthdate DATE;
BEGIN
    birthdate := TO_DATE(:NEW.BIRTH, 'DD/MM/YYYY');

    IF birthdate >= TRUNC(SYSDATE) THEN
        RAISE_APPLICATION_ERROR(-20001, 'Ngày sinh không hợp lệ. Vui lòng kiểm tra lại.');
    END IF;
END;
/
-- Ngày tạo hóa đơn bằng ngày hiện tại
DROP TRIGGER TRG_ORDER_DATE
CREATE OR REPLACE TRIGGER TRG_ORDER_DATE
BEFORE INSERT ON INVOICE
FOR EACH ROW
BEGIN
    :NEW.ORDER_DATE := TRUNC(SYSDATE);
END;
/

-- Khách hàng phải mua sản phẩm mới được đánh giá
CREATE OR REPLACE TRIGGER trg_check_product_purchase
BEFORE INSERT ON ProductReview
FOR EACH ROW
DECLARE
    v_count NUMBER;
BEGIN
    -- Kiểm tra xem khách hàng đã mua sản phẩm này chưa
    SELECT COUNT(*)
    INTO v_count
    FROM INVOICE_DETAILS
    WHERE USERID = :NEW.USERID
        AND PRODUCT_ID = :NEW.PRODUCTID;

    -- Nếu khách hàng chưa mua sản phẩm, ngăn việc chèn dữ liệu mới
    IF v_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Khách hàng phải mua sản phẩm trước khi đánh giá.');
    END IF;
END;
/


--- ======================================================================== ---
    --- ----                Stored Procedure                 ---- ---
-- Lấy ra thông tin product
CREATE OR REPLACE PROCEDURE GetProductDetails(
    p_product_id IN PRODUCTS.ID%TYPE
)
AS
    v_product_details PRODUCTS%ROWTYPE;
BEGIN
    SELECT *
    INTO v_product_details
    FROM PRODUCTS
    WHERE ID = p_product_id;
    
    -- In thông tin chi tiết của sản phẩm
    DBMS_OUTPUT.PUT_LINE('Product ID: ' || v_product_details.ID);
    DBMS_OUTPUT.PUT_LINE('Product Name: ' || v_product_details.PRODUCT_NAME);
    DBMS_OUTPUT.PUT_LINE('Category: ' || v_product_details.CATEGORY);
    DBMS_OUTPUT.PUT_LINE('Price: ' || v_product_details.PRODUCT_PRICE);
    DBMS_OUTPUT.PUT_LINE('Image URL: ' || v_product_details.IMG_URL);
    DBMS_OUTPUT.PUT_LINE('Description: ' || v_product_details.DESCRIPTION);
    DBMS_OUTPUT.PUT_LINE('Quantity: ' || v_product_details.QUANTITY);
    DBMS_OUTPUT.PUT_LINE('Brand Name: ' || v_product_details.BRAND_NAME);
    DBMS_OUTPUT.PUT_LINE('Designer: ' || v_product_details.DESIGNER);
END;
/
-- Thực thi
BEGIN
    GetProductDetails(4);
END;
/

--------------------------------------------------------------------------------

-- Thêm vào giỏ hàng
CREATE OR REPLACE PROCEDURE AddToCart (
    p_UserID IN CART.USER_ID%TYPE,
    p_ProductID IN CART_DETAILS.PRODUCT_ID%TYPE,
    p_Quantity IN CART_DETAILS.QUANTITY%TYPE
)
IS
    v_CartID CART.CARTID%TYPE;
    v_total CART_DETAILS.TOTAL%TYPE;
BEGIN
    -- Lấy mã giỏ hàng của người dùng
    SELECT CARTID INTO v_CartID
    FROM CART
    WHERE USER_ID = p_UserID;
    
    -- Lấy giá sản phẩm từ bảng PRODUCTS
    SELECT PRODUCT_PRICE INTO v_Total
    FROM PRODUCTS
    WHERE ID = p_ProductID;

    -- Tính toán total
    v_Total := v_Total * p_Quantity;
    -- Thêm chi tiết giỏ hàng  
    INSERT INTO CART_DETAILS (QUANTITY, TOTAL, CARTID, PRODUCT_ID)
    VALUES (p_Quantity, v_total, v_CartID, p_ProductID);

    -- Cập nhật tổng trị giá của giỏ hàng
    UPDATE CART
    SET TOTAL = (
        SELECT SUM(CART_DETAILS.QUANTITY * PRODUCTS.PRODUCT_PRICE)
        FROM CART_DETAILS
        INNER JOIN PRODUCTS ON CART_DETAILS.PRODUCT_ID = PRODUCTS.ID
        WHERE CART_DETAILS.CARTID = v_CartID
    )
    WHERE CARTID = v_CartID;
    
    -- In thông tin sản phẩm đã được thêm vào giỏ hàng
    DBMS_OUTPUT.PUT_LINE('Sản phẩm đã được thêm vào giỏ hàng.');
    DBMS_OUTPUT.PUT_LINE('Mã sản phẩm: ' || p_ProductID);
    DBMS_OUTPUT.PUT_LINE('Số lượng: ' || p_Quantity);
    DBMS_OUTPUT.PUT_LINE('Giỏ hàng ID: ' || v_CartID);
END;
/
-- Thực thi thêm vào giỏ hàng
BEGIN
    -- Gọi stored procedure AddToCart
    --AddToCart(v_UserID, v_ProductID, v_Quantity);
    AddToCart(283, 17, 2);
    commit;
END;

--------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE CreateInvoice(p_UserID IN NUMBER)
AS
  v_CartID NUMBER;
  v_InvoiceID NUMBER;
  v_ProductID NUMBER;
  v_Quantity NUMBER;
  v_Price NUMBER;
  
BEGIN
    begin
  -- Tạo một hóa đơn mới
  INSERT INTO INVOICE (USER_ID, STATUS)
  VALUES (p_UserID, 'notpaid')
  RETURNING INVOICEID INTO v_InvoiceID;
  
  -- Lấy mã giỏ hàng của người dùng
  SELECT CARTID INTO v_CartID FROM CART WHERE USER_ID = p_UserID;
  
  -- Duyệt qua từng sản phẩm trong giỏ hàng và tạo chi tiết hóa đơn
    FOR cart_detail IN (SELECT CD.PRODUCT_ID, CD.QUANTITY, P.PRODUCT_PRICE
                        FROM CART_DETAILS CD
                        INNER JOIN PRODUCTS P ON CD.PRODUCT_ID = P.ID
                        WHERE CD.CARTID = v_CartID) 
    LOOP
      v_ProductID := cart_detail.PRODUCT_ID;
      v_Quantity := cart_detail.QUANTITY;
      v_Price := v_Quantity * cart_detail.PRODUCT_PRICE ;
    
    -- Tạo chi tiết hóa đơn
    INSERT INTO INVOICE_DETAILS (INVOICEID, PRODUCT_ID, QUANTITY, PRICE)
    VALUES (v_InvoiceID, v_ProductID, v_Quantity, v_Price);
   END LOOP; 
   
   ClearCartItems(v_CartID);
    -- Commit giao dịch
    COMMIT;
    
    -- Hiển thị thông báo thành công
    DBMS_OUTPUT.PUT_LINE('Hóa đơn đã được tạo. Mã hóa đơn: ' || v_InvoiceID);
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      -- Xử lý khi không tìm thấy giỏ hàng
      DBMS_OUTPUT.PUT_LINE('Không tìm thấy giỏ hàng cho người dùng có ID: ' || p_UserID);
    WHEN OTHERS THEN
      -- Xử lý các lỗi khác
      DBMS_OUTPUT.PUT_LINE('Đã xảy ra lỗi trong quá trình tạo hóa đơn.');
      ROLLBACK;
  END;
END;

-- Thực thi
BEGIN
    -- Gọi stored procedure CreateInvoice
    --CreateInvoice(v_UserID);
    CreateInvoice(283);
END;

--------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE ClearCartItems(p_CartID IN NUMBER) AS
BEGIN
  DELETE FROM CART_DETAILS WHERE CARTID = p_CartID;
  COMMIT;
END;

--------------------------------------------------------------------------------
---- Lấy ra chi tiết hóa đơn 
CREATE OR REPLACE PROCEDURE GetInvoice(p_InvoiceID IN INVOICE.INVOICEID%TYPE) IS
    CURSOR c_InvoiceDetails IS
        SELECT ID.PRODUCT_ID, ID.QUANTITY, ID.PRICE
        FROM INVOICE_DETAILS ID
        WHERE ID.INVOICEID = p_InvoiceID;
BEGIN
    -- Lấy thông tin hóa đơn
    FOR i IN (SELECT I.INVOICEID, I.USER_ID, I.STATUS, I.TOTAL_PRICE
              FROM INVOICE I
              WHERE I.INVOICEID = p_InvoiceID)
    LOOP
        -- In thông tin hóa đơn
        DBMS_OUTPUT.PUT_LINE('InvoiceID: ' || i.INVOICEID);
        DBMS_OUTPUT.PUT_LINE('User ID: ' || i.USER_ID);
        DBMS_OUTPUT.PUT_LINE('Status: ' || i.STATUS);
        DBMS_OUTPUT.PUT_LINE('Total Price: ' || i.TOTAL_PRICE);
        DBMS_OUTPUT.PUT_LINE('List product in Invoice: ');
        -- In thông tin chi tiết hóa đơn
        FOR cd IN c_InvoiceDetails
        LOOP
            DBMS_OUTPUT.PUT_LINE('------------------------');
            DBMS_OUTPUT.PUT_LINE('Product ID: ' || cd.PRODUCT_ID);
            DBMS_OUTPUT.PUT_LINE('Quantity: ' || cd.QUANTITY);
            DBMS_OUTPUT.PUT_LINE('Price: ' || cd.PRICE);
            
        END LOOP;
    END LOOP;
END;
/

BEGIN
    GetInvoice(155);
END;
/
--------------------------------------------------------------------------------
---- Lấy ra các hóa đơn của user
CREATE OR REPLACE PROCEDURE GetUserInvoices(
    p_UserID IN users.id%TYPE
)
IS
BEGIN
        DBMS_OUTPUT.PUT_LINE('User ID: ' || p_UserID);
        DBMS_OUTPUT.PUT_LINE('List Invoice of User: ' );
        DBMS_OUTPUT.PUT_LINE('------------------------');
    FOR invoice_rec IN (
        SELECT i.INVOICEID, i.USER_ID, i.STATUS, i.TOTAL_PRICE
        FROM INVOICE i
        WHERE i.USER_ID = p_UserID
    )
    LOOP
        DBMS_OUTPUT.PUT_LINE('InvoiceID: ' || invoice_rec.INVOICEID);
        DBMS_OUTPUT.PUT_LINE('Status: ' || invoice_rec.STATUS);
        DBMS_OUTPUT.PUT_LINE('Total Price: ' || invoice_rec.TOTAL_PRICE);
        DBMS_OUTPUT.PUT_LINE('------------------------');
    END LOOP;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Không tìm thấy hóa đơn cho người dùng có ID: ' || p_UserID);
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Đã xảy ra lỗi trong quá trình lấy hóa đơn.');
END;
/
BEGIN
    GetUserInvoices(283);
END;
/
--------------------------------------------------------------------------------
--- Lấy ra giỏ hàng
CREATE OR REPLACE PROCEDURE GetCartDetails(p_UserID IN CART.USER_ID%TYPE) IS
  CURSOR cart_cursor IS
    SELECT CD.CART_DETAILSID, CD.PRODUCT_ID, CD.QUANTITY, CD.TOTAL
    FROM CART_DETAILS CD
    INNER JOIN CART C ON CD.CARTID = C.CARTID
    WHERE C.USER_ID = p_UserID;
  
  cart_item cart_cursor%ROWTYPE;
BEGIN
  OPEN cart_cursor;
  LOOP
    FETCH cart_cursor INTO cart_item;
    EXIT WHEN cart_cursor%NOTFOUND;
    
    DBMS_OUTPUT.PUT_LINE('Cart Detail ID: ' || cart_item.CART_DETAILSID);
    DBMS_OUTPUT.PUT_LINE('Product ID: ' || cart_item.PRODUCT_ID);
    DBMS_OUTPUT.PUT_LINE('Quantity: ' || cart_item.QUANTITY);
    DBMS_OUTPUT.PUT_LINE('Total: ' || cart_item.TOTAL);
    DBMS_OUTPUT.PUT_LINE('------------------------');
  END LOOP;
  CLOSE cart_cursor;
END;

/
begin 
    GetCartDetails(283);
end;

--------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE GetTopSellingProducts(p_TopCount IN NUMBER) AS
  TYPE ProductSalesRecord IS RECORD (
    product_id PRODUCTS.ID%TYPE,
    product_name PRODUCTS.PRODUCT_NAME%TYPE,
    total_sold NUMBER
  );
  TYPE ProductSalesTable IS TABLE OF ProductSalesRecord;
  v_ProductSales ProductSalesTable;
BEGIN
  SELECT P.ID, P.PRODUCT_NAME, SUM(ID.QUANTITY) AS TOTAL_SOLD
  BULK COLLECT INTO v_ProductSales
  FROM INVOICE_DETAILS ID
  INNER JOIN PRODUCTS P ON ID.PRODUCT_ID = P.ID
  GROUP BY P.ID, P.PRODUCT_NAME
  ORDER BY TOTAL_SOLD DESC;
  
  -- Lấy danh sách top sản phẩm bán chạy nhất
  FOR i IN 1..LEAST(p_TopCount, v_ProductSales.COUNT) LOOP
    DBMS_OUTPUT.PUT_LINE('Product ID: ' || v_ProductSales(i).product_id || ', Product Name: ' || v_ProductSales(i).product_name || ', Total Sold: ' || v_ProductSales(i).total_sold);
  END LOOP;
END;
/

EXECUTE GetTopSellingProducts(5);

--------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE GetTopSellingProductsByMonth(p_Month IN NUMBER, p_Year IN NUMBER, p_TopCount IN NUMBER)
AS
BEGIN
DBMS_OUTPUT.PUT_LINE('Top ' || p_TopCount || ' product of ' || p_Month || '/' || p_Year || ': ');
  FOR product IN (
    SELECT P.ID, P.PRODUCT_NAME, SUM(INVOICE_DETAILS.QUANTITY) AS TOTAL_SOLD
    FROM INVOICE_DETAILS 
    INNER JOIN PRODUCTS P ON INVOICE_DETAILS.PRODUCT_ID = P.ID
    INNER JOIN INVOICE  ON INVOICE_DETAILS.INVOICEID = INVOICE.INVOICEID
    WHERE EXTRACT(MONTH FROM INVOICE.ORDER_DATE) = p_Month
      AND EXTRACT(YEAR FROM INVOICE.ORDER_DATE) = p_Year
    GROUP BY P.ID, P.PRODUCT_NAME
    ORDER BY TOTAL_SOLD DESC
    FETCH FIRST p_TopCount ROWS ONLY
  ) LOOP
    DBMS_OUTPUT.PUT_LINE('Product ID: ' || product.ID || ', Product Name: ' || product.PRODUCT_NAME || ', Total Sold: ' || product.TOTAL_SOLD);
  END LOOP;
END;
/

BEGIN
  GetTopSellingProductsByMonth(6, 2023, 10); -- Lấy top 10 sản phẩm bán chạy trong tháng 6/2023
END;
/













