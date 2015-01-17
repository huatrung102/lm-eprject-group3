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
		ORDER BY c.Cat_Name
	END
GO

USE [LMS]
GO
Create PROCEDURE [dbo].[Categories_Insert]
@Cat_Name NVARCHAR(30),
@Cat_isDelete BIT,
@Cat_Description NVARCHAR(100)
AS
 BEGIN
 IF EXISTS (SELECT * FROM Categories WHERE Cat_Name = @Cat_Name)
  RETURN 0;
 begin transaction
 begin try  
   INSERT INTO [dbo].[Categories]
     ([Cat_Name]
     ,[Cat_isDelete]
     ,[Cat_Description])
  VALUES
     (@Cat_Name
     , @Cat_isDelete
     , @Cat_Description)
 end try
 begin catch
  if @@trancount >0
   rollback transaction;
 end catch
  if @@trancount >0
  begin
   commit transaction;
   return 1;
  end
  else
   return 2;
 END
 GO
 
USE [LMS]
GO
CREATE PROCEDURE [dbo].[Categories_getCategoryByCateId] 
	@Cat_Id NVARCHAR(36)
AS
BEGIN
	SELECT * FROM Categories c WHERE c.Cat_Id = @Cat_Id
END
GO


USE [LMS]
GO
CREATE PROCEDURE [dbo].[Categories_getCategoryByCateName] 
	@Cat_Name NVARCHAR(36)
AS
BEGIN
	SELECT * FROM Categories c WHERE c.Cat_Name = @Cat_Name
END
GO


USE [LMS]
GO
CREATE PROCEDURE Categories_Lock
@Cat_Id NVARCHAR(36)
AS
	BEGIN
 begin transaction
 begin try  
  UPDATE [dbo].[Categories]
		   SET
			  [Cat_isDelete] = 1
		WHERE Cat_Id = @Cat_Id
 end try
 begin catch
  if @@trancount >0
   rollback transaction;
 end catch
  if @@trancount >0
  begin
   commit transaction;
   return 1;
  end
  else
   return 2;
   END
GO

USE [LMS]
GO
CREATE PROCEDURE Categories_Update
	@Cat_Id NVARCHAR(36),
	@Cat_Name NVARCHAR(30),
	@Cat_isDelete BIT,
	@Cat_Description NVARCHAR(100)
AS
 BEGIN
 IF EXISTS (SELECT * FROM Categories WHERE Cat_Name = @Cat_Name)
  RETURN 0;
 begin transaction
 begin try  
   UPDATE [dbo].[Categories]
	   SET 
		  [Cat_Name] = @Cat_Name
		  ,[Cat_isDelete] = @Cat_isDelete
		  ,[Cat_Description] = @Cat_Description
	 WHERE Cat_Id = @Cat_Id
 end try
 begin catch
  if @@trancount >0
   rollback transaction;
 end catch
  if @@trancount >0
  begin
   commit transaction;
   return 1;
  end
  else
   return 2;
   END
GO

CREATE PROCEDURE Categories_getCategoryList
AS
BEGIN
	SELECT c.Cat_Name AS 'Category'
		FROM Categories c
		WHERE c.Cat_isDelete = 0	--Cat_IsDeleted = 0 (khong bi xoa) - 1 (xoa)
		ORDER BY c.Cat_Id
END
GO



CREATE PROCEDURE Books_Insert
	@Book_ISBN VARCHAR(13),
	@Book_Title nvarchar(50),
	@Book_Publisher nvarchar(50),
	@Book_Author nvarchar(50),
	@Book_Price MONEY,
	@Book_Content nvarchar(1000),
	@Cat_Id char(36),
	@Book_Language varchar(7),
	@Book_ImageFile varchar(255),
	@Book_isDelete BIT
AS
 BEGIN
 begin transaction
 begin try  
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
           ,[Book_isDeleted])
     VALUES	 
           (@Book_ISBN,
           	@Book_Title,
			@Book_Publisher,
			@Book_Author,
			@Book_Price,
			@Book_Content,
			@Cat_Id,
			@Book_Language,
			@Book_ImageFile,
			@Book_isDelete)
 end try
 begin catch
  if @@trancount >0
   rollback transaction;
 end catch
  if @@trancount >0
  begin
   commit transaction;
   return 1;
  end
  else
   return 2;
 END
 GO
 


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[view_Book_Cate_Copy]
AS
SELECT        B.Book_ISBN, B.Book_Title, B.Book_Publisher, B.Book_Author, B.Book_Price, B.Book_Content, C.Cat_Name, B.Book_Language, B.Book_ImageFile, B.Book_CreateDate, COP.Cop_No, COP.Cop_Status, 
                         COP.Cop_Id, COP.Cop_isDeleted, B.Book_isDeleted, B.Cat_Id
FROM            dbo.Books AS B LEFT OUTER JOIN
                         dbo.Categories AS C ON C.Cat_Id = B.Cat_Id LEFT OUTER JOIN
                         dbo.Copies AS COP ON COP.Book_ISBN = B.Book_ISBN

GO


CREATE PROCEDURE Books_getBookListByCatename
@Cat_Name NVARCHAR(30)
AS
BEGIN
	SELECT [Book_ISBN]
	  ,[Book_Title] AS 'Title'
      ,[Book_Author] AS 'Author'
      ,[Book_Publisher] AS 'Publisher'
      ,CASE WHEN [Book_isDeleted] = 0 THEN 'Active' ELSE 'Inactive' end AS 'Status'
     
  FROM view_Book_Cate_Copy
	WHERE Cat_Name = @Cat_Name
END
GO

CREATE PROCEDURE Books_searchBook
@Book_Title nvarchar(50) = NULL
,@Book_Publisher nvarchar(50) = NULL
,@Book_Author nvarchar(50) = NULL
,@Book_isDeleted CHAR = '-1'
AS
BEGIN
SELECT [Book_Title] AS 'Title'
      ,[Book_Author] AS 'Author'
      ,[Book_Publisher] AS 'Publisher'
      ,CASE WHEN [Book_isDeleted] = 0 THEN 'Active' ELSE 'Inactive' end AS 'Status'
  FROM Books
WHERE 
	Book_Title LIKE (CASE WHEN @Book_Title='' OR @Book_Title IS NULL THEN Book_Title ELSE '%'+@Book_Title+'%' END)
	AND Book_Author LIKE (CASE WHEN @Book_Author='' OR @Book_Author IS NULL THEN Book_Author ELSE '%'+@Book_Author+'%' END)
	AND Book_Publisher LIKE (CASE WHEN @Book_Publisher='' OR @Book_Publisher IS NULL THEN Book_Publisher ELSE '%'+@Book_Publisher+'%' END)
	AND Book_isDeleted = (CASE WHEN @Book_isDeleted = '-1' THEN Book_isDeleted ELSE @Book_isDeleted END)
END
GO


CREATE PROCEDURE [dbo].[Books_getByISBN]
@Book_ISBN varchar(13)
AS
BEGIN
	SELECT b.[Book_ISBN]
      ,b.[Book_Title]
      ,b.[Book_Publisher]
      ,b.[Book_Author]
      ,b.[Book_Price]
      ,b.[Book_Content]
      ,b.[Cat_Id]
      ,b.[Book_Language]
      ,b.[Book_ImageFile]
      ,b.[Book_CreateDate]
      ,b.[Book_isDeleted]
      ,bcc.[Cat_Name]
      , COUNT(BCC.Cop_Id) 'Book_Count'
	FROM Books B LEFT JOIN view_Book_Cate_Copy BCC
		ON B.Book_ISBN = BCC.Book_ISBN
	WHERE 
			B.Book_ISBN = @Book_ISBN
	GROUP BY b.[Book_ISBN]
		,b.[Book_Title]
		,b.[Book_Publisher]
		,b.[Book_Author]
		,b.[Book_Price]
		,b.[Book_Content]
		,b.[Cat_Id]
		,b.[Book_Language]
		,b.[Book_ImageFile]
		,b.[Book_CreateDate]
		,b.[Book_isDeleted]
		,BCC.Cat_Name
		
END
GO

CREATE PROCEDURE Books_Update
	@Book_ISBN VARCHAR(13),
	@Book_Title nvarchar(50),
	@Book_Publisher nvarchar(50),
	@Book_Author nvarchar(50),
	@Book_Price MONEY,
	@Book_Content nvarchar(1000),
	@Cat_Id char(36),
	@Book_Language varchar(7),
	@Book_ImageFile varchar(255),
	@Book_isDelete BIT
AS
 BEGIN
 begin transaction
 BEGIN TRY
   UPDATE [dbo].Books
	   SET 
			[Book_ISBN] = @Book_ISBN,
           	[Book_Title] = @Book_Title,
			[Book_Publisher] = @Book_Publisher,
			[Book_Author] = @Book_Author,
			[Book_Price] = @Book_Price,
			[Book_Content] = @Book_Content,
			[Cat_Id] = @Cat_Id,
			[Book_Language] = @Book_Language,
			[Book_ImageFile] = @Book_ImageFile,
			[Book_isDeleted] = @Book_isDelete
	 WHERE Book_ISBN = @Book_ISBN  
 end try
 begin catch
  if @@trancount >0
   rollback transaction;
 end catch
  if @@trancount >0
  begin
   commit transaction;
   return 1;
  end
  else
   return 2;
 END
 GO
 
 INSERT INTO [dbo].[Copies]
           (
           [Cop_No]
           ,[Book_ISBN]
           ,[Cop_Status]
           ,[Cop_isDeleted])
     VALUES
           (
           '12345678'
           ,'0987654321123'
           ,0
           ,0)
 GO
 
 CREATE PROCEDURE Copies_Insert
	@Book_ISBN VARCHAR (13)
	,@Cop_No CHAR (15)
AS
 BEGIN
 begin transaction
 begin try  
INSERT INTO [dbo].[Copies]
           ([Cop_No]
           ,[Book_ISBN]
           ,[Cop_Status]
           ,[Cop_isDeleted])
     VALUES
           (@Cop_No
           ,@Book_ISBN
           ,1
           ,0)
 end try
 begin catch
  if @@trancount >0
   rollback transaction;
 end catch
  if @@trancount >0
  begin
   commit transaction;
   return 1;
  end
  else
   return 2;
 END
GO



DELETE FROM Fines
DELETE FROM IRBookDetails
DELETE FROM Copies
DELETE FROM Books