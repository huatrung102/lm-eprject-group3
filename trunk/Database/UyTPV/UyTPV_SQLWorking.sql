USE LMS
GO

INSERT INTO [dbo].[Categories]
           ([Cat_Id]
           ,[Cat_Name]
           ,[Cat_isDelete]
           ,[Cat_Description])
     VALUES
           (1,N'Novel', 0, N'Novel'),
		   (2,N'Economic', 0, N'Economic'),
		   (3,N'Specialization', 0, N'Specialization'),
		   (4,N'Life Style', 0, N'Life Style'),
		   (5,N'Schoolbook', 0, N'Schoolbook'),
		   (6,N'Dictionary', 0, N'Dictionary'),
		   (7,N'Culture and Art', 0, N'Culture and Art'),
		   (8,N'Manga - Comic', 0, N'Manga - Comic'),
		   (9,N'Travel', 0, N'Travel'),
		   (10,N'Magazine', 0, N'Magazine'),
		   (0,N'Khác', 0, N'Thể loại Khác')     
GO

USE [LMS]
GO

INSERT INTO [dbo].[Books]
           ([Book_ISBN]
           ,[Book_Title]
           ,[Book_Publisher]
           ,[Book_Author]
           ,[Book_Price]
           ,[Book_Content]
           ,[Cat_Id]
           ,[Book_Language]
           ,[Book_ImageFile]
           ,[Book_CreateDate]
           ,[Book_isDeleted])
     VALUES
			('1111111111111','Book1','pub1','auth1',1,'content1',1,'vn','img/aaa.jpg',2015/01/01,0),
			('2222222222222','Book2','pub2','auth2',2,'content2',2,'en','img/bbb.jpg',2015/02/02,0),
			('3333333333333','Book3','pub3','auth3',3,'content3',3,'fr','img/ccc.jpg',2015/03/03,0),
			('4444444444444','Book4','pub4','auth4',4,'content4',3,'us','img/ddd.jpg',2015/04/04,0)
GO

USE LMS
GO
CREATE PROCEDURE [Categories_getCategoryListWithBookNumber]
AS
	BEGIN
		SELECT c.Cat_Id AS 'Category ID', c.Cat_Name AS 'Category', isnull(COUNT(b.Book_ISBN),0) AS 'Book Count'
		FROM Categories c
		LEFT JOIN Books b ON c.Cat_Id = b.Cat_Id
		WHERE c.Cat_isDelete = 0	--Cat_IsDeleted = 0 (khong bi xoa) - 1 (xoa)
		GROUP BY c.Cat_Id, c.Cat_Name
		ORDER BY c.Cat_Id
	END
GO

USE [LMS]
GO
CREATE PROCEDURE [Categories_Insert]
@Cat_Name NVARCHAR(30),
@Cat_isDelete BIT,
@Cat_Description NVARCHAR(100)
AS
	BEGIN
		INSERT INTO [dbo].[Categories]
				   ([Cat_Name]
				   ,[Cat_isDelete]
				   ,[Cat_Description])
			 VALUES
				   --(@Cat_Name
				   --, @Cat_isDelete
				   --, @Cat_Description)
				   ('Lala',0,'asda')
	END
GO

CREATE PROCEDURE [Categories_findCategoryByCateName]
@Cat_Name NVARCHAR(30)
AS
	BEGIN
		SELECT * FROM Categories c WHERE c.Cat_Name = @Cat_Name
	END
GO