-- ================================================
-- Template generated from Template Explorer using:
-- Create Procedure (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- This block of comments will not be included in
-- the definition of the procedure.
-- ================================================
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		@Author,,Name>
-- Create date: @Create Date,
-- Description:	@Description,
-- =============================================
CREATE PROCEDURE Members_Insert
			
           @Mem_FirstName nvarchar(30)
           ,@Mem_LastName nvarchar(50)
           ,@Mem_Phone varchar(20)
           ,@Mem_Address nvarchar(200)
           ,@Mem_Email varchar(50)
           ,@Mem_ImageFile varchar(255)
AS
BEGIN
	INSERT INTO [dbo].[Members]
           ([Mem_FirstName]
           ,[Mem_LastName]
           ,[Mem_Phone]
           ,[Mem_Address]
           ,[Mem_Email]
           ,[Mem_ImageFile])
     VALUES
           (@Mem_FirstName
           ,@Mem_LastName
           ,@Mem_Phone
           ,@Mem_Address
           ,@Mem_Email
           ,@Mem_ImageFile)

END
GO

CREATE PROCEDURE Staffs_Insert

			@Staff_FirstName nvarchar(30)
           ,@Staff_LastName nvarchar(50)
           ,@Staff_Login varchar(25)
           ,@Staff_Password varchar(50)
           ,@Staff_Phone varchar(20)
           ,@Staff_Role varchar(15)
           ,@Staff_Address nvarchar(200)
           ,@Staff_Email varchar(50)
           ,@Staff_Status bit
           ,@Staff_CreateDate datetime
           ,@Staff_isDelete bit
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


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROC [dbo].[getProfile]
@Staff_Id varchar(36)
AS
	BEGIN
		SELECT Staff_Id,Staff_FirstName,Staff_LastName,Staff_Login,Staff_Password,Staff_Phone,Staff_Role,Staff_Address,Staff_Email,Staff_Status,Staff_CreateDate
		FROM [Staffs]
		where Staff_Id = @Staff_Id
	END
GO


CREATE PROCEDURE Members_Update
		@Mem_Id char(36)
      ,	@Mem_FirstName nvarchar(30)
      ,	@Mem_LastName nvarchar(50)
      , @Mem_Phone varchar(20)
      , @Mem_Address nvarchar(200)
      ,	@Mem_Email varchar(50)
      , @Mem_ImageFile varchar(255)
AS
BEGIN
	UPDATE [dbo].[Members]
   SET [Mem_FirstName] = @Mem_FirstName
      ,[Mem_LastName] = @Mem_LastName
      ,[Mem_Phone] = @Mem_Phone
      ,[Mem_Address] = @Mem_Address
      ,[Mem_Email] = @Mem_Email
      ,[Mem_ImageFile] = @Mem_ImageFile
 WHERE @Mem_Id = Mem_Id
END
GO

CREATE PROCEDURE Staffs_Update
		@Staff_Id char(36)
      ,@Staff_FirstName nvarchar(30)
      ,@Staff_LastName nvarchar(50)
      ,@Staff_Login varchar(25)
      ,@Staff_Password varchar(50)
      ,@Staff_Phone varchar(20)
      ,@Staff_Role varchar(15)
      ,@Staff_Address nvarchar(200)
      ,@Staff_Email varchar(50)
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
      ,[Staff_ImageFile] = @Staff_ImageFile
 WHERE @Staff_Id = Staff_Id
END
GO

CREATE PROCEDURE Members_Lock
	@Mem_Id char(36)
AS
BEGIN
	UPDATE [dbo].[Members]
   SET
   
   [Mem_isDelete] = 1
      
 WHERE @Mem_Id = Mem_Id
END
GO


CREATE PROCEDURE Staffs_Lock
	@Staff_Id char(36)
AS
BEGIN
	UPDATE [dbo].[Staffs]
   SET 
   
   [Staff_isDelete] = 1

 WHERE @Staff_Id = Staff_Id
END
GO


CREATE PROCEDURE Staffs_ChangePassword
	 @Staff_Login varchar(25)
	,@Staff_Password varchar(50)
AS
BEGIN
	UPDATE [dbo].[Staffs]
   SET 
   
   [Staff_Password] = @Staff_Password
      
 WHERE @Staff_Login = Staff_Login
END
GO


CREATE PROCEDURE Staffs_GetByParameters
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