USE [LMS]
GO
/****** Object:  StoredProcedure [dbo].[Books_DeleteBookByISBN]    Script Date: 1/19/2015 5:32:28 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Books_getBookListByCatename]    Script Date: 1/19/2015 5:32:28 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Books_getByISBN]    Script Date: 1/19/2015 5:32:28 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Books_Insert]    Script Date: 1/19/2015 5:32:28 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Books_searchBook]    Script Date: 1/19/2015 5:32:28 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Books_Update]    Script Date: 1/19/2015 5:32:28 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Categories_getCategoryByCateId]    Script Date: 1/19/2015 5:32:28 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Categories_getCategoryByCateName]    Script Date: 1/19/2015 5:32:28 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Categories_getCategoryList]    Script Date: 1/19/2015 5:32:28 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Categories_getCategoryListWithBookNumber]    Script Date: 1/19/2015 5:32:28 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Categories_Insert]    Script Date: 1/19/2015 5:32:28 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Categories_Lock]    Script Date: 1/19/2015 5:32:28 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Categories_Update]    Script Date: 1/19/2015 5:32:28 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Copies_DeleteCopiesByISBN]    Script Date: 1/19/2015 5:32:28 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Copies_Insert]    Script Date: 1/19/2015 5:32:28 PM ******/
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
/****** Object:  StoredProcedure [dbo].[getProfile]    Script Date: 1/19/2015 5:32:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[getProfile]
@Staff_Id varchar(36)
AS
	BEGIN
		SELECT Staff_Id,Staff_FirstName,Staff_LastName,Staff_Login,Staff_Password,Staff_Phone,Staff_Role,Staff_Address,Staff_Email,
		Staff_Status
		,Staff_ImageFile
		,Staff_CreateDate
		FROM [Staffs]
		where Staff_Id = @Staff_Id
	END


GO
/****** Object:  StoredProcedure [dbo].[Members_getMemberByMemberId]    Script Date: 1/19/2015 5:32:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[Members_getMemberByMemberId]
	@Mem_Id CHAR(36)
AS
BEGIN
	SELECT * FROM Members WHERE Mem_Id = @Mem_Id
END



GO
/****** Object:  StoredProcedure [dbo].[Members_Insert]    Script Date: 1/19/2015 5:32:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		@Author,,Name>
-- Create date: @Create Date,
-- Description:	@Description,
-- =============================================
CREATE PROCEDURE [dbo].[Members_Insert]	
           @Mem_FirstName nvarchar(30)
           ,@Mem_LastName nvarchar(50)
           ,@Mem_Phone varchar(20)
           ,@Mem_Address nvarchar(200)
           ,@Mem_Email varchar(50)
           ,@Mem_ImageFile varchar(255)
AS
 BEGIN
 begin transaction
 begin try  
   INSERT INTO [dbo].[Members]
           ([Mem_FirstName]
           ,[Mem_LastName]
           ,Mem_No
           ,[Mem_Phone]
           ,[Mem_Address]
           ,[Mem_Email]
           ,[Mem_ImageFile])
     VALUES
           (@Mem_FirstName
           ,@Mem_LastName
           ,dbo.returnNextMemNo()
           ,@Mem_Phone
           ,@Mem_Address
           ,@Mem_Email
           ,@Mem_ImageFile)
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
/****** Object:  StoredProcedure [dbo].[Members_Lock]    Script Date: 1/19/2015 5:32:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Members_Lock]
	@Mem_Id char(36)
AS
BEGIN
	UPDATE [dbo].[Members]
   SET
   
   [Mem_isDelete] = 1
      
 WHERE Mem_Id = @Mem_Id
END


GO
/****** Object:  StoredProcedure [dbo].[Members_Update]    Script Date: 1/19/2015 5:32:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[Members_Update]
      @Mem_Id char(36)
      ,	@Mem_FirstName nvarchar(30)
      ,	@Mem_LastName nvarchar(50)
      , @Mem_Phone varchar(20)
      , @Mem_Address nvarchar(200)
      ,	@Mem_Email varchar(50)
      , @Mem_ImageFile varchar(255)
      , @Mem_Status BIT
AS
 BEGIN
 begin transaction
 begin try  
   UPDATE [dbo].[Members]
   SET [Mem_FirstName] = @Mem_FirstName
      ,[Mem_LastName] = @Mem_LastName
      ,[Mem_Phone] = @Mem_Phone
      ,[Mem_Address] = @Mem_Address
      ,[Mem_Email] = @Mem_Email
      ,[Mem_ImageFile] = @Mem_ImageFile
      ,Mem_Status = @Mem_Status
	WHERE Mem_Id = @Mem_Id
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
/****** Object:  StoredProcedure [dbo].[Mems_getMemberList]    Script Date: 1/19/2015 5:32:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Mems_getMemberList]
AS
BEGIN
	SELECT [Mem_Id]
      ,[Mem_FirstName]
      ,[Mem_LastName]
      ,[Mem_No]
      ,[Mem_Phone]
      ,[Mem_Address]
      ,[Mem_Email]
      ,case when Mem_Status = 1 then 'Active' else 'InActive' end AS 'Status'
      --,[Mem_CreateDate]
      --,[Mem_isDelete]
      --,[Mem_ImageFile]
	FROM [dbo].[Members] 
END


GO
/****** Object:  StoredProcedure [dbo].[Staffs_ChangePassword]    Script Date: 1/19/2015 5:32:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Staffs_ChangePassword]
	 @Staff_Login varchar(25)
	,@Staff_Password varchar(50)
	,@Staff_NewPassword varchar(50)
AS
BEGIN
	IF not exists(select * from STAFFS where  Staff_Login = @Staff_Login and Staff_Password = @Staff_Password)
		RETURN 0 --Thông báo mật khẩu cũ sai!
	ELSE
	BEGIN TRAN
		BEGIN TRY
			UPDATE [dbo].[Staffs]

			SET [Staff_Password] = @Staff_NewPassword

			WHERE  Staff_Login = @Staff_Login
		END TRY
	BEGIN CATCH
		IF @@trancount>0
			ROLLBACK TRAN;
		END CATCH
			IF @@TRANCOUNT>0
		BEGIN
			COMMIT TRAN
			RETURN 1; --THANHCONG !
		END
	ELSE
		RETURN 2;--FAIL ! 
 END




GO
/****** Object:  StoredProcedure [dbo].[Staffs_GetByParameters]    Script Date: 1/19/2015 5:32:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Staffs_GetByParameters]
@Staff_Id char(8) = ''
,@Staff_Firstname nvarchar(80) = '' 
,@Staff_Email varchar(50) = ''
,@Staff_Login varchar(20) = ''
AS
BEGIN
		SELECT 
		[Staff_Id]
      ,[Staff_FirstName]
      ,[Staff_LastName]
      ,[Staff_Phone]
      ,[Staff_Address]
      ,[Staff_Email]
      ,[Staff_Status]
      ,[Staff_CreateDate]
      ,[Staff_isDelete]
      ,[Staff_ImageFile]
  FROM [dbo].[Staffs]
  WHERE [Staff_isDelete] = 0
  AND Staff_Id = (CASE WHEN @Staff_Id  = '' THEN Staff_Id ELSE @Staff_Id END)
  AND (Staff_FirstName LIKE (CASE WHEN @Staff_Firstname  = '' THEN Staff_FirstName ELSE @Staff_Firstname END) 
			OR	Staff_LastName  LIKE (CASE WHEN @Staff_Firstname  = '' THEN Staff_LastName ELSE @Staff_Firstname END))
  AND Staff_Email = (CASE WHEN @Staff_Email  = '' THEN Staff_Email ELSE @Staff_Email END)
  AND Staff_Phone = (CASE WHEN @Staff_Login  = '' THEN Staff_Phone ELSE @Staff_Login END)
END



GO
/****** Object:  StoredProcedure [dbo].[Staffs_getListStaff]    Script Date: 1/19/2015 5:32:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[Staffs_getListStaff]
AS
BEGIN
	SELECT [Staff_Id]  
      ,[Staff_FirstName]'First Name'
      ,[Staff_LastName] 'Last Name'
      ,[Staff_Login]'Login'
      --,[Staff_Password]
	  -- ,[Staff_Phone]
      ,[Staff_Role] 'Role'
      --,[Staff_Address]
      ,[Staff_Email] 'Email' 
      ,case when Staff_Status = 1 then 'Active' else 'InActive' end 'Status'
      --,[Staff_CreateDate]
      --,[Staff_isDelete]
      --,[Staff_ImageFile]
  FROM [dbo].[Staffs]
END


GO
/****** Object:  StoredProcedure [dbo].[Staffs_getStaffbyStaffId]    Script Date: 1/19/2015 5:32:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[Staffs_getStaffbyStaffId]
	@Staff_Id CHAR(36)
AS
BEGIN
	SELECT * FROM Staffs WHERE Staff_Id = @Staff_Id
END



GO
/****** Object:  StoredProcedure [dbo].[Staffs_getStaffListbyStaffId]    Script Date: 1/19/2015 5:32:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Staffs_getStaffListbyStaffId]
@Staff_Id CHAR(36)
AS
BEGIN
	SELECT [Staff_Id]
      ,[Staff_FirstName]
      ,[Staff_LastName]
      ,[Staff_Login]
      --,[Staff_Password]
      --,[Staff_Phone]
      ,[Staff_Role]
      --,[Staff_Address]
      ,[Staff_Email]
      ,case when Staff_Status = 1 then 'Active' else 'InActive' end 'Status'
      --,[Staff_CreateDate]
      --,[Staff_isDelete]
      --,[Staff_ImageFile]
  FROM [dbo].[Staffs]
	WHERE Staff_Id = @Staff_Id
	GROUP BY Staff_Id,Staff_FirstName, Staff_LastName, Staff_Email, Staff_Login,Staff_Role,Staff_Status
END



GO
/****** Object:  StoredProcedure [dbo].[Staffs_Insert]    Script Date: 1/19/2015 5:32:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Staffs_Insert]

			@Staff_FirstName nvarchar(30)
           ,@Staff_LastName nvarchar(50)
           ,@Staff_Login varchar(25)
           ,@Staff_Password varchar(50)
           ,@Staff_Phone varchar(20)
           ,@Staff_Role varchar(15)
           ,@Staff_Address nvarchar(200)
           ,@Staff_Email varchar(50)
           --,@Staff_Status bit
           --,@Staff_CreateDate datetime
           --,@Staff_isDelete bit
           ,@Staff_ImageFile varchar(255)

AS
BEGIN
	IF EXISTS (SELECT * FROM Staffs WHERE Staff_Login = @Staff_Login)
		RETURN 0;
	IF EXISTS (SELECT * FROM Staffs WHERE Staff_Email = @Staff_Email)
		RETURN 3;
	BEGIN TRAN
		BEGIN TRY
			INSERT INTO [dbo].[Staffs]
				([Staff_FirstName]
			   ,[Staff_LastName]
			   ,[Staff_Login]
			   ,[Staff_Password]
			   ,[Staff_Phone]
			   ,[Staff_Role]
			   ,[Staff_Address]
			   ,[Staff_Email]
			   ,[Staff_ImageFile])
			VALUES
			   (@Staff_FirstName
			   ,@Staff_LastName
			   ,@Staff_Login 
			   ,@Staff_Password
			   ,@Staff_Phone
			   ,@Staff_Role 
			   ,@Staff_Address 
			   ,@Staff_Email 
			   ,@Staff_ImageFile)
		END TRY
	BEGIN CATCH
		IF @@trancount>0
		ROLLBACK TRAN;
	END CATCH
	IF @@TRANCOUNT>0
		BEGIN
			COMMIT TRAN
			RETURN 1; --THANHCONG
		END
	ELSE
		RETURN 2;--FAIL
END



GO
/****** Object:  StoredProcedure [dbo].[Staffs_Lock]    Script Date: 1/19/2015 5:32:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Staffs_Lock]
	@Staff_Id char(36)
AS
BEGIN
	UPDATE [dbo].[Staffs]
   SET 
   
   [Staff_isDelete] = 1

 WHERE @Staff_Id = Staff_Id
END


GO
/****** Object:  StoredProcedure [dbo].[Staffs_Login]    Script Date: 1/19/2015 5:32:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[Staffs_Login]
	@Staff_Login varchar(25)
	,@Staff_Password varchar(50)
AS
BEGIN
	if exists (select * from Staffs where Staff_Login = @Staff_Login)
	if exists(select * from STAFFS where  Staff_Login = @Staff_Login and Staff_Password = @Staff_Password)
		SELECT 1 'check',* from STAFFS where  Staff_Login = @Staff_Login and Staff_Password = @Staff_Password
	ELSE
		SELECT 2 'check'
else
select 0 'check'
END


GO
/****** Object:  StoredProcedure [dbo].[Staffs_Update]    Script Date: 1/19/2015 5:32:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Staffs_Update]
		@Staff_Id char(36)
      ,@Staff_FirstName nvarchar(30)
      ,@Staff_LastName nvarchar(50)
      ,@Staff_Login varchar(25)
      ,@Staff_Password varchar(50)
      ,@Staff_Phone varchar(20)
      ,@Staff_Role varchar(15)
      ,@Staff_Address nvarchar(200)
      ,@Staff_Email varchar(50)
	  ,@Staff_Status bit
      ,@Staff_ImageFile varchar(255)
AS
BEGIN
	UPDATE [dbo].[Staffs]
   SET	[Staff_FirstName] = @Staff_FirstName
      ,[Staff_LastName] = @Staff_LastName
      ,[Staff_Login] = @Staff_Login
      ,[Staff_Password] = @Staff_Password
      ,[Staff_Phone] = @Staff_Phone
      ,[Staff_Role] = @Staff_Role
      ,[Staff_Address] = @Staff_Address
      ,[Staff_Email] = @Staff_Email
	  ,[Staff_Status] = @Staff_Status
      ,[Staff_ImageFile] = @Staff_ImageFile
 WHERE Staff_Id = @Staff_Id
END


GO
/****** Object:  UserDefinedFunction [dbo].[returnNextMemNo]    Script Date: 1/19/2015 5:32:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		TRUNGHTH
-- Create date: <Create Date, ,>
-- Description:	<Description, ,>
-- =============================================
CREATE FUNCTION [dbo].[returnNextMemNo]
(
	
)
RETURNS CHAR(8)
AS
BEGIN
	DECLARE @MAXINT INT;
	DECLARE @NEWNO CHAR(8)
	SELECT @MAXINT = (CONVERT(INT,RIGHT(M.Mem_No,7))+1) FROM Members m;
	IF(ISNULL(@MAXINT,0) =0)
		SET @NEWNO = 'M' + '0000001' ;
	ELSE
		SET @NEWNO = 'M' + RIGHT('0000000' + CONVERT(VARCHAR(7),@MAXINT),7) ;
	RETURN @NEWNO;
END

GO
/****** Object:  View [dbo].[view_Book_Cate_Copy]    Script Date: 1/19/2015 5:32:28 PM ******/
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
