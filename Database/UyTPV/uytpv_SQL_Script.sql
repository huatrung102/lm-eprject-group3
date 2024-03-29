USE [LMS]
GO
/****** Object:  StoredProcedure [dbo].[Books_DeleteBookByISBN]    Script Date: 1/20/2015 11:11:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Books_DeleteBookByISBN]
	@Book_ISBN VARCHAR (13)
AS
 BEGIN
 IF EXISTS (SELECT * FROM Copies WHERE Book_ISBN = @Book_ISBN AND Cop_Status = 0)
  RETURN 0;
 begin transaction
 begin try  
	UPDATE Copies 
	SET Cop_isDeleted = 1 
	WHERE Book_ISBN = @Book_ISBN
	
	UPDATE Books
	SET Book_isDeleted = 1
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
/****** Object:  StoredProcedure [dbo].[Books_getBookListByCatename]    Script Date: 1/20/2015 11:11:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Books_getBookListByCatename]
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
	GROUP BY Book_ISBN, Book_Title, Book_Author, Book_Publisher, Book_isDeleted
END

GO
/****** Object:  StoredProcedure [dbo].[Books_getByISBN]    Script Date: 1/20/2015 11:11:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
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
/****** Object:  StoredProcedure [dbo].[Books_Insert]    Script Date: 1/20/2015 11:11:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Books_Insert]
	@Book_ISBN VARCHAR(13),
	@Book_Title nvarchar(50),
	@Book_Publisher nvarchar(50),
	@Book_Author nvarchar(50),
	@Book_Price float,
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

 DELETE FROM Books
 SELECT * FROM Books b
GO
/****** Object:  StoredProcedure [dbo].[Books_searchBook]    Script Date: 1/20/2015 11:11:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Books_searchBook]
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
/****** Object:  StoredProcedure [dbo].[Books_Update]    Script Date: 1/20/2015 11:11:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Books_Update]
	@Book_ISBN VARCHAR(13),
	@Book_Title nvarchar(50),
	@Book_Publisher nvarchar(50),
	@Book_Author nvarchar(50),
	@Book_Price float,
	@Book_Content nvarchar(1000),
	@Cat_Id char(36),
	@Book_Language varchar(7),
	@Book_ImageFile varchar(255),
	@Book_isDelete BIT
AS
 BEGIN
 begin transaction
 BEGIN TRY
	UPDATE Books
	   SET 
		  [Book_Title] = @Book_Title
           ,[Book_Publisher] = @Book_Publisher
           ,[Book_Author] = @Book_Author
           ,[Book_Price] = @Book_Price
           ,[Book_Content] = @Book_Content
           ,[Cat_Id] = @Cat_Id
           ,[Book_Language] = @Book_Language
           ,[Book_ImageFile] = @Book_ImageFile
           ,[Book_isDeleted] = @Book_isDelete
	 WHERE [Book_ISBN] = @Book_ISBN
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

 DELETE FROM Books
 SELECT * FROM Books b
GO
/****** Object:  StoredProcedure [dbo].[Categories_getCategoryByCateId]    Script Date: 1/20/2015 11:11:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[Categories_getCategoryByCateId] 
	@Cat_Id NVARCHAR(36)
AS
BEGIN
	SELECT * FROM Categories c WHERE c.Cat_Id = @Cat_Id
END

GO
/****** Object:  StoredProcedure [dbo].[Categories_getCategoryByCateName]    Script Date: 1/20/2015 11:11:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Categories_getCategoryByCateName] 
	@Cat_Name NVARCHAR(36)
AS
BEGIN
	SELECT * FROM Categories c WHERE c.Cat_Name = @Cat_Name
END

GO
/****** Object:  StoredProcedure [dbo].[Categories_getCategoryList]    Script Date: 1/20/2015 11:11:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Categories_getCategoryList]
AS
BEGIN
	SELECT c.Cat_Name AS 'Category'
		FROM Categories c
		WHERE c.Cat_isDelete = 0	--Cat_IsDeleted = 0 (khong bi xoa) - 1 (xoa)
		ORDER BY c.Cat_Id
END

GO
/****** Object:  StoredProcedure [dbo].[Categories_getCategoryListWithBookNumber]    Script Date: 1/20/2015 11:11:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Categories_getCategoryListWithBookNumber]
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
/****** Object:  StoredProcedure [dbo].[Categories_Insert]    Script Date: 1/20/2015 11:11:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Categories_Insert]
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
/****** Object:  StoredProcedure [dbo].[Categories_Lock]    Script Date: 1/20/2015 11:11:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Categories_Lock]
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
/****** Object:  StoredProcedure [dbo].[Categories_Update]    Script Date: 1/20/2015 11:11:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Categories_Update]
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
/****** Object:  StoredProcedure [dbo].[Copies_DeleteCopiesByISBN]    Script Date: 1/20/2015 11:11:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[Copies_DeleteCopiesByISBN]
	@Book_ISBN VARCHAR (13)
AS
 BEGIN
 IF EXISTS (SELECT * FROM Copies WHERE Book_ISBN = @Book_ISBN AND Cop_Status = 1)
  RETURN 0;
 begin transaction
 begin try  
	UPDATE Copies 
	SET Cop_Status = 1 
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
/****** Object:  StoredProcedure [dbo].[Copies_Insert]    Script Date: 1/20/2015 11:11:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
 CREATE PROCEDURE [dbo].[Copies_Insert]
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
/****** Object:  Table [dbo].[Books]    Script Date: 1/20/2015 11:11:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Books](
	[Book_ISBN] [varchar](13) NOT NULL,
	[Book_Title] [nvarchar](50) NOT NULL,
	[Book_Publisher] [nvarchar](50) NULL,
	[Book_Author] [nvarchar](50) NULL,
	[Book_Price] [money] NOT NULL,
	[Book_Content] [nvarchar](1000) NOT NULL,
	[Cat_Id] [char](36) NULL,
	[Book_Language] [varchar](20) NOT NULL,
	[Book_ImageFile] [varchar](255) NOT NULL,
	[Book_CreateDate] [datetime] NOT NULL,
	[Book_isDeleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Book_ISBN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 1/20/2015 11:11:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Categories](
	[Cat_Id] [char](36) NOT NULL,
	[Cat_Name] [nvarchar](30) NOT NULL,
	[Cat_isDelete] [bit] NOT NULL,
	[Cat_Description] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[Cat_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Cat_Name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Copies]    Script Date: 1/20/2015 11:11:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Copies](
	[Cop_Id] [char](36) NOT NULL,
	[Cop_No] [char](15) NOT NULL,
	[Book_ISBN] [varchar](13) NULL,
	[Cop_Status] [bit] NOT NULL,
	[Cop_isDeleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Cop_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Cop_No] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  View [dbo].[view_Book_Cate_Copy]    Script Date: 1/20/2015 11:11:16 AM ******/
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
ALTER TABLE [dbo].[Books] ADD  DEFAULT ('') FOR [Book_Content]
GO
ALTER TABLE [dbo].[Books] ADD  DEFAULT ('/image/nocover.png') FOR [Book_ImageFile]
GO
ALTER TABLE [dbo].[Books] ADD  DEFAULT (getdate()) FOR [Book_CreateDate]
GO
ALTER TABLE [dbo].[Books] ADD  DEFAULT ((0)) FOR [Book_isDeleted]
GO
ALTER TABLE [dbo].[Categories] ADD  DEFAULT (newid()) FOR [Cat_Id]
GO
ALTER TABLE [dbo].[Categories] ADD  DEFAULT ((0)) FOR [Cat_isDelete]
GO
ALTER TABLE [dbo].[Copies] ADD  DEFAULT (newid()) FOR [Cop_Id]
GO
ALTER TABLE [dbo].[Copies] ADD  DEFAULT ((1)) FOR [Cop_Status]
GO
ALTER TABLE [dbo].[Copies] ADD  DEFAULT ((0)) FOR [Cop_isDeleted]
GO
ALTER TABLE [dbo].[Books]  WITH CHECK ADD FOREIGN KEY([Cat_Id])
REFERENCES [dbo].[Categories] ([Cat_Id])
GO
ALTER TABLE [dbo].[Copies]  WITH CHECK ADD FOREIGN KEY([Book_ISBN])
REFERENCES [dbo].[Books] ([Book_ISBN])
GO
