USE [LMS]
GO
/****** Object:  StoredProcedure [dbo].[getProfile]    Script Date: 1/20/2015 10:49:40 AM ******/
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
/****** Object:  StoredProcedure [dbo].[Members_GetByID]    Script Date: 1/20/2015 10:49:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		TRUNGHTH
-- Create date: <Create Date,,>
-- Description:	GET MEMBER BY ID
-- =============================================
Create PROCEDURE [dbo].[Members_GetByID]
@Mem_Id CHAR(36)
AS
BEGIN
		SELECT [Mem_Id]
      ,[Mem_FirstName]
      ,[Mem_LastName]
      ,[Mem_No]
      ,[Mem_Phone]
      ,[Mem_Address]
      ,[Mem_Email]
      ,[Mem_Status]
      ,[Mem_CreateDate]
      ,[Mem_isDelete]
      ,[Mem_ImageFile]
  FROM [dbo].[Members]
  WHERE [Mem_isDelete] = 0
  AND Mem_Id = @Mem_Id
  
END


GO
/****** Object:  StoredProcedure [dbo].[Members_GetByParameters]    Script Date: 1/20/2015 10:49:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		TRUNGHTH
-- Create date: <Create Date,,>
-- Description:	GET MEMBER BY PARAMETER
-- =============================================
CREATE PROCEDURE [dbo].[Members_GetByParameters]
@Mem_No char(8) = ''
,@Mem_name nvarchar(80) = '' 
,@Mem_Email varchar(50) = ''
,@Mem_Phone varchar(20) = ''
AS
BEGIN
		SELECT [Mem_Id]
      ,[Mem_FirstName]
      ,[Mem_LastName]
      ,[Mem_No]
      ,[Mem_Phone]
      ,[Mem_Address]
      ,[Mem_Email]
      ,[Mem_Status]
      ,[Mem_CreateDate]
      ,[Mem_isDelete]
      ,[Mem_ImageFile]
  FROM [dbo].[Members]
  WHERE [Mem_isDelete] = 0
  AND Mem_No = (CASE WHEN @Mem_No  = '' THEN Mem_No ELSE @Mem_No END)
  AND (Mem_FirstName LIKE (CASE WHEN @Mem_name  = '' THEN Mem_FirstName ELSE '%'+ @Mem_name+'%' END) 
			OR	Mem_LastName  LIKE (CASE WHEN @Mem_name  = '' THEN Mem_LastName ELSE '%' +@Mem_name+'%' END))
  AND Mem_Email = (CASE WHEN @Mem_Email  = '' THEN Mem_Email ELSE @Mem_Email END)
  AND Mem_Phone = (CASE WHEN @Mem_Phone  = '' THEN Mem_Phone ELSE @Mem_Phone END)
END


GO
/****** Object:  StoredProcedure [dbo].[Members_Insert]    Script Date: 1/20/2015 10:49:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Members_Insert]

			@Mem_FirstName nvarchar(30)
           ,@Mem_LastName nvarchar(50)
           ,@Mem_Phone varchar(20)
           ,@Mem_Address nvarchar(200)
           ,@Mem_Email varchar(50)
           ,@Mem_ImageFile varchar(255)
           

AS
BEGIN
	
	IF EXISTS (SELECT * FROM Members WHERE Mem_Email = @Mem_Email)
		RETURN 3;
	BEGIN TRAN
		BEGIN TRY
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
/****** Object:  StoredProcedure [dbo].[Members_Lock]    Script Date: 1/20/2015 10:49:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[Members_Lock]
      @Mem_Id char(36)
AS
 BEGIN
 begin transaction
 begin try  
   UPDATE [dbo].[Members]
   SET Mem_isDelete = 1
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
/****** Object:  StoredProcedure [dbo].[Members_Update]    Script Date: 1/20/2015 10:49:40 AM ******/
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
/****** Object:  StoredProcedure [dbo].[Mems_getMemberList]    Script Date: 1/20/2015 10:49:40 AM ******/
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
/****** Object:  StoredProcedure [dbo].[Staffs_ChangePassword]    Script Date: 1/20/2015 10:49:40 AM ******/
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
/****** Object:  StoredProcedure [dbo].[Staffs_GetByParameters]    Script Date: 1/20/2015 10:49:40 AM ******/
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
/****** Object:  StoredProcedure [dbo].[Staffs_getListStaff]    Script Date: 1/20/2015 10:49:40 AM ******/
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
	  where [Staff_isDelete] = 0
  
END

GO
/****** Object:  StoredProcedure [dbo].[Staffs_getStaffbyStaffId]    Script Date: 1/20/2015 10:49:40 AM ******/
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
/****** Object:  StoredProcedure [dbo].[Staffs_getStaffListbyStaffId]    Script Date: 1/20/2015 10:49:40 AM ******/
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
/****** Object:  StoredProcedure [dbo].[Staffs_Insert]    Script Date: 1/20/2015 10:49:40 AM ******/
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
/****** Object:  StoredProcedure [dbo].[Staffs_Lock]    Script Date: 1/20/2015 10:49:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Staffs_Lock]
      @Staff_Id char(36)
AS
 BEGIN
 begin transaction
 begin try  
   UPDATE [dbo].[Staffs]
   SET Staff_isDelete = 1
	WHERE Staff_Id = @Staff_Id
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
/****** Object:  StoredProcedure [dbo].[Staffs_Login]    Script Date: 1/20/2015 10:49:40 AM ******/
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
/****** Object:  StoredProcedure [dbo].[Staffs_Update]    Script Date: 1/20/2015 10:49:40 AM ******/
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
