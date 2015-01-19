USE [LMS]
GO
/****** Object:  StoredProcedure [dbo].[Books_DeleteBookByISBN]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Books_getBookListByCatename]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Books_getByISBN]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Books_getListBookByFilter]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		trunghth
-- Create date: <Create Date,,>
-- Description:	get list book by filter
-- =============================================
CREATE PROCEDURE [dbo].[Books_getListBookByFilter]
@Book_ISBN varchar(13) = ''
,@Book_Title nvarchar(50) = ''
,@Book_Author nvarchar(50) = ''
AS
BEGIN
		SELECT 
					B.[Book_ISBN] 'ISBN'
					,B.[Book_Title] 'Title'
					,B.Book_Author 'Author'
		FROM view_Book_Cate_Copy B
		
		WHERE B.[Book_ISBN] LIKE (CASE WHEN @Book_ISBN = '' THEN B.[Book_ISBN] ELSE '%'+@Book_ISBN+'%' END)
			AND	B.Book_Title LIKE (CASE WHEN @Book_Title = '' THEN B.Book_Title ELSE '%'+@Book_Title+'%' END)
			AND	B.Book_Author like (CASE WHEN @Book_Author = '' THEN B.Book_Author ELSE '%'+@Book_Author+'%' END)
			AND Book_isDeleted = 0
		group by B.[Book_ISBN] 
					,B.[Book_Title] 
					,B.Book_Author 

END

GO
/****** Object:  StoredProcedure [dbo].[Books_Insert]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Books_searchBook]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Books_Update]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Categories_getCategoryByCateId]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Categories_getCategoryByCateName]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Categories_getCategoryList]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Categories_getCategoryListWithBookNumber]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Categories_Insert]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Categories_Lock]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Categories_Update]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Copies_DeleteCopiesByISBN]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Copies_getLastestIsFree]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		trunghth
-- Create date: <Create Date,,>
-- Description:	get lastest copy which is free
-- =============================================
CREATE PROCEDURE [dbo].[Copies_getLastestIsFree]
@Book_ISBN varchar(13)
,@Cop_IdList varchar(500)
AS
BEGIN
		SELECT TOP(1) Cop_No ,Cop_Id,Book_ISBN,Cop_Status,Cop_isDeleted
		FROM [Copies] 
		WHERE [Book_ISBN] = @Book_ISBN
			AND [Cop_Status] = 1
			AND [Cop_isDeleted] = 0
			AND Cop_Id NOT IN ( SELECT ITEMS FROM Split(@Cop_IdList,','))
		order by NEWID()
END
GO
/****** Object:  StoredProcedure [dbo].[Copies_Insert]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Fines_ListByMemberNo]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		trunghth
-- Create date: <Create Date,,>
-- Description:	paid fee fine
-- =============================================
CREATE PROCEDURE [dbo].[Fines_ListByMemberNo]
@Mem_No char(8)

--,@Fine_Id char(36)
AS
BEGIN
	SELECT [Fine_Id] 'ID'
      ,bcc.Book_ISBN 'ISBN'
	    ,bcc.Book_Title 'Title'
		,bcc.Cop_No 'Copy No'
		,bcc.Book_Author 'Author'
      ,[Fine_Amount] 'Money'
      ,[Fine_CreateDate] 'CreateDate'
     
      
   
   
	FROM Fines f LEFT JOIN [dbo].[view_IR_IRDetail_Member] irm
	ON irm.IRBookDetail_id = f.IRBookDetail_Id LEFT JOIN [dbo].[view_Book_Cate_Copy] bcc
	ON bcc.Cop_Id = irm.Cop_Id
	WHERE [Fine_Status] = 0
	AND Mem_No = @Mem_No
END

GO
/****** Object:  StoredProcedure [dbo].[Fines_Paid]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		trunghth
-- Create date: <Create Date,,>
-- Description:	paid fee fine
-- =============================================
CREATE PROCEDURE [dbo].[Fines_Paid]
@Fine_Id varchar(500)
AS
BEGIN
	BEGIN TRAN
		BEGIN TRY			
			UPDATE f
				SET Fine_PaidDate = GETDATE()
					,Fine_Status = 1
			from Fines f inner join dbo.Split(@Fine_Id,',') s
			on s.items = f.Fine_Id
			WHERE  Fine_Status = 0
		END TRY
		BEGIN CATCH
		IF @@trancount>0
		ROLLBACK TRAN;
		END CATCH
		IF @@trancount>0
			BEGIN
				COMMIT TRAN;
				RETURN 1; -- THANH CONG
			END
		ELSE
		RETURN 2; -- ERROR 
	
END

GO
/****** Object:  StoredProcedure [dbo].[getProfile]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROC [dbo].[getProfile]
@Staff_Id varchar(36)
AS
	BEGIN
		SELECT Staff_Id,Staff_FirstName,Staff_LastName,Staff_Login,Staff_Password,Staff_Phone,Staff_Role,Staff_Address,Staff_Email,Staff_Status,Staff_CreateDate
		FROM [Staffs]
		where Staff_Id = @Staff_Id
	END

GO
/****** Object:  StoredProcedure [dbo].[IRBooks_getListBookNotReturn]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		trunghth
-- Create date: <Create Date,,>
-- Description:	view all issued book which not return yet
-- =============================================
CREATE PROCEDURE [dbo].[IRBooks_getListBookNotReturn]
@Mem_No char(8)
AS
BEGIN
	SELECT 
	'0' as '√ Select all'
	,IRM.IRBookDetail_Id 
     ,Row_number() over (order by [IRBook_CreateDate]) 'No'
     ,BCC.Book_ISBN 'ISBN'
     ,BCC.Book_Title 'Title'
		,BCC.Cop_No 'Cop No'
     ,CONVERT(VARCHAR(10), [IRBook_CreateDate],103) 'IssueDate'
      ,CONVERT(VARCHAR(10), [IRBook_DueDate],103) 'DueDate'
	,case when DATEDIFF(DAY,[IRBook_DueDate],GETDATE()) >= 0 THEN 	 DATEDIFF(DAY,[IRBook_DueDate],GETDATE())
						ELSE 0 END 'Late Day'
				
	   FROM [view_IR_IRDetail_Member] IRM 
	   LEFT JOIN [view_Book_Cate_Copy] BCC ON IRM.Cop_Id = BCC.Cop_Id
	   
	   WHERE	IRM.IRBookDetail_Status = 0 
	   AND irm.Mem_No = @Mem_No
END
GO
/****** Object:  StoredProcedure [dbo].[IRBooks_getListBookNotReturnByMemberNo]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		trunghth
-- Create date: <Create Date,,>
-- Description:	view all issued book which not return yet
-- =============================================
CREATE PROCEDURE [dbo].[IRBooks_getListBookNotReturnByMemberNo]
@Mem_No char(8)
AS
BEGIN
	SELECT 
	--1 as ' '
     Row_number() over (order by [IRBook_CreateDate]) 'No'
     ,CONVERT(VARCHAR(10), [IRBook_CreateDate],103) 'CreateDate'
      ,CONVERT(VARCHAR(10), [IRBook_DueDate],103) 'DueDate'
		,BCC.Book_ISBN 'ISBN'
		,BCC.Book_Title 'Title'
		,BCC.Cop_No 'Cop No'
		
	   FROM [view_IR_IRDetail_Member] IRM 
	   LEFT JOIN [view_Book_Cate_Copy] BCC ON IRM.Cop_Id = BCC.Cop_Id
	   
	   WHERE	IRM.IRBookDetail_Status = 0 
	   AND irm.Mem_No = @Mem_No
END
GO
/****** Object:  StoredProcedure [dbo].[IRBooks_getSummary]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		trunghth
-- Create date: <Create Date,,>
-- Description:	view all issued book which not return yet
-- =============================================
CREATE PROCEDURE [dbo].[IRBooks_getSummary]

AS
BEGIN
	SELECT(
	SELECT COUNT(IRM.Cop_Id) FROM view_IR_IRDetail_Member IRM
	WHERE CONVERT(VARCHAR(10),IRM.IRBook_CreateDate,103)  = CONVERT(VARCHAR(10),GETDATE(),103) 
	GROUP BY CONVERT (varchar(10),IRM.IRBook_CreateDate,103) 
	 ) 'ISUUED',
	(SELECT COUNT(IRM.Cop_Id) FROM view_IR_IRDetail_Member IRM
	WHERE CONVERT(VARCHAR(10),IRM.IRBookDetail_ReturnDate,103)  = CONVERT(VARCHAR(10),GETDATE(),103) 
	GROUP BY CONVERT (varchar(10),IRM.IRBookDetail_ReturnDate,103)
	) 'RETURNED'
END
GO
/****** Object:  StoredProcedure [dbo].[IRBooks_IssueBook]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		TRUNGHTH
-- Create date: 09/01/2015
-- Description:	ISSUE BOOK
-- =============================================
CREATE PROCEDURE [dbo].[IRBooks_IssueBook]
@Cop_No varchar(500)
,@Mem_Id char(36)	
AS

DECLARE @IRBook_Id char(36)
DECLARE @CountTemp int
DECLARE @Cop_Id CHAR(36)
DECLARE @CountCopyID int
BEGIN
	SELECT @Cop_Id = Cop_id FROM Copies WHERE Cop_No = @Cop_No;
	SELECT @CountTemp = COUNT(*) 
			FROM IRBooks IR LEFT JOIN
				IRBookDetails IRD ON IR.IRBook_Id = IRD.IRBook_Id
			WHERE IRD.IRBookDetail_Status = 0
			GROUP BY IR.MEM_ID
	SELECT @CountCopyID = COUNT(*) FROM dbo.Split(@Cop_No,',')
	IF (@CountTemp + @CountCopyID <= 5 OR @CountTemp IS NULL)	--KIEM TRA CO VUOT QUA SO LUONG MUON CHO PHEP HAY CHUA
	BEGIN
		--EXEC @ReturnValue =  IRBooks_Insert @Mem_Id,@duedate,@IRBook_Id OUTPUT	
		--INSERT IRBooks
		SET @IRBook_Id = NEWID();
		BEGIN TRAN
			BEGIN TRY			
				INSERT INTO [IRBooks]
				   ([IRBook_Id]
				   ,[Mem_Id]				   
				   ,[IRBook_DueDate])
				 VALUES
				   (@IRBook_Id
				   ,@Mem_Id				  
				   ,GETDATE() + 7) -- ONE WEEK
				 
				INSERT INTO [IRBookDetails]
			   ([IRBookDetail_Id]
			   ,[IRBook_Id]
			   ,[Cop_Id]
			    )			   
				SELECT NEWID(),@IRBook_Id,b.Cop_Id FROM dbo.Split(@Cop_No,',') a LEFT JOIN Copies b
				ON a.items = b.Cop_No				
				
				UPDATE c
					SET c.Cop_Status = 0
				FROM Copies c INNER JOIN dbo.Split(@Cop_No,',') s
				ON s.items = c.Cop_No
				
		END TRY
		BEGIN CATCH
			IF @@trancount>0
			ROLLBACK TRAN;
		END CATCH
		IF @@trancount>0
			BEGIN
				COMMIT TRAN;
				RETURN 1; -- THANH CONG
			END
		ELSE
		RETURN 2; -- ERROR 
	END
	ELSE
		RETURN 0; --ERROR VUOT QUA SO LUONG COPY CHO MUON
		
END
GO
/****** Object:  StoredProcedure [dbo].[IRBooks_ReturnBook]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		TRUNGHTH
-- Create date: 09/01/2015
-- Description:	Return BOOK
-- =============================================
CREATE PROCEDURE [dbo].[IRBooks_ReturnBook]
@IRBookDetail_Id  varchar(500)
--,@Fine_Amount decimal(7,2)
--,@lateday int
AS
BEGIN

	
	--KIEM TRA CO TON TAI RECORD CHUA TRA SACH HAY KHONG??
	BEGIN		
		BEGIN TRAN
			BEGIN TRY			
			UPDATE  IRD	
					SET IRBookDetail_Status = 1
					,IRBookDetail_ReturnDate = GETDATE()
			From  IRBookDetails IRD inner join dbo.Split(@IRBookDetail_Id,',') s
			on s.items = IRD.IRBookDetail_Id			

			UPDATE c
			SET c.Cop_Status = 1
			FROM IRBookDetails ir INNER JOIN Copies c
			ON c.Cop_Id = ir.Cop_Id inner join dbo.Split(@IRBookDetail_Id,',') s
			on s.items = ir.IRBookDetail_Id
			where c.Cop_Status = 0
			
			
			INSERT INTO Fines (IRBookDetail_Id,Fine_Amount)	
			select items,(DATEDIFF(DAY,irm.IRBook_DueDate,GETDATE()) * (bcc.Book_Price/100)) 
			from  dbo.Split(@IRBookDetail_Id,',') s inner join [dbo].[view_IR_IRDetail_Member] irm
			on irm.IRBookDetail_Id = s.items inner join [dbo].[view_Book_Cate_Copy] bcc
			on bcc.Cop_Id = irm.Cop_Id
			where DATEDIFF(DAY,irm.IRBook_DueDate,GETDATE()) > 0
						
			END TRY
		BEGIN CATCH
			IF @@trancount>0
			ROLLBACK TRAN;
		END CATCH
		IF @@trancount>0
			BEGIN
				COMMIT TRAN;
				RETURN 1; -- THANH CONG
			END
		ELSE
		RETURN 2; -- ERROR 
	END
END
GO
/****** Object:  StoredProcedure [dbo].[Members_GetByID]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Members_GetByParameters]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Members_GetFilterBySearchBox]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		TRUNGHTH
-- Create date: <Create Date,,>
-- Description:	GET MEMBER BY PARAMETER
-- =============================================
CREATE PROCEDURE [dbo].[Members_GetFilterBySearchBox]

@Mem_name nvarchar(80) = '' 
,@Mem_Email varchar(50) = ''
,@Mem_Phone varchar(20) = ''
AS
BEGIN
		SELECT [Mem_Id]
		,[Mem_No] 'Member No'
      ,[Mem_FirstName] +' ' + [Mem_LastName] 'Full name'
      
      ,[Mem_Email] 'Email' 
      ,[Mem_Phone] 'Phone'
  FROM [dbo].[Members]
  WHERE [Mem_isDelete] = 0

  AND (Mem_FirstName LIKE (CASE WHEN @Mem_name  = '' THEN Mem_FirstName ELSE '%'+@Mem_name+'%' END) 
			OR	Mem_LastName  LIKE (CASE WHEN @Mem_name  = '' THEN Mem_LastName ELSE  '%'+@Mem_name+'%' END))
  AND Mem_Email = (CASE WHEN @Mem_Email  = '' THEN Mem_Email ELSE @Mem_Email END)
  AND Mem_Phone = (CASE WHEN @Mem_Phone  = '' THEN Mem_Phone ELSE @Mem_Phone END)
END
GO
/****** Object:  StoredProcedure [dbo].[Members_GetIRCountInformation]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Members_GetIRCountInformation]
@Mem_No char(8)

AS
BEGIN
	SELECT MEM.[Mem_Id]
      ,[Mem_FirstName]
      ,[Mem_LastName]
      ,[Mem_No]
      ,[Mem_Phone]
      ,[Mem_Address]
      ,[Mem_Email]
      ,[Mem_Status]
      ,CONVERT(VARCHAR(10),[Mem_CreateDate] ,103) 'Mem_CreateDate'
      ,[Mem_isDelete]
      ,[Mem_ImageFile]
	  ,ISNULL([Book Issued],0) 'Count_Issued'
	   FROM [dbo].[Members] MEM LEFT JOIN 
		  (	  SELECT M.[Mem_Id]
						,COUNT(Cop_Id) 'Book Issued'
			FROM [dbo].[Members] M
			LEFT JOIN IRBooks IR ON IR.Mem_Id = M.Mem_Id
			LEFT JOIN IRBookDetails IRD ON IRD.IRBook_Id = IR.IRBook_Id 
			WHERE IRD.IRBookDetail_Status = 0
			GROUP BY M.[Mem_Id]) TEMP

		ON TEMP.Mem_Id = MEM.Mem_Id
	WHERE Mem_No = @Mem_No
END
GO
/****** Object:  StoredProcedure [dbo].[Members_Insert]    Script Date: 19/01/2015 11:59:07 PM ******/
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

END

GO
/****** Object:  StoredProcedure [dbo].[Members_Lock]    Script Date: 19/01/2015 11:59:07 PM ******/
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
      
 WHERE @Mem_Id = Mem_Id
END

GO
/****** Object:  StoredProcedure [dbo].[Members_Update]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Mems_getMemberList]    Script Date: 19/01/2015 11:59:07 PM ******/
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
      ,case when Mem_Status = 1 then 'Active' else 'InActive' end 'Status'
      --,[Mem_CreateDate]
      --,[Mem_isDelete]
      --,[Mem_ImageFile]
	FROM [dbo].[Members] 
END

GO
/****** Object:  StoredProcedure [dbo].[Staffs_ChangePassword]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[Staffs_ChangePassword]
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
/****** Object:  StoredProcedure [dbo].[Staffs_GetByParameters]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Staffs_getListStaff]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Staffs_getStaffbyStaffId]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Staffs_getStaffListbyStaffId]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Staffs_Insert]    Script Date: 19/01/2015 11:59:07 PM ******/
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
         --  ,@Staff_Status bit
          -- ,@Staff_CreateDate datetime
          -- ,@Staff_isDelete bit
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
/****** Object:  StoredProcedure [dbo].[Staffs_Lock]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Staffs_Login]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


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
/****** Object:  StoredProcedure [dbo].[Staffs_Update]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  UserDefinedFunction [dbo].[returnNextMemNo]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  UserDefinedFunction [dbo].[Split]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		trunghth
-- Create date: <Create Date,,>
-- Description:	return table
-- =============================================
CREATE FUNCTION [dbo].[Split]
(	
	@String varchar(500) 
	, @Delimeter char(1)

)
RETURNS @tempTable TABLE (items VARCHAR(500)) 
AS
BEGIN
DECLARE @index int
DECLARE @slice varchar(500)

SELECT @index = 1
		IF LEN(@String) < 1 OR @String IS NULL RETURN
WHILE @index != 0
BEGIN
	SET @index = CHARINDEX(@Delimeter,@String)
	IF @index != 0
		SET @slice = LEFT(@String , @index - 1)
	ELSE
		SET @slice = @String
	IF(LEN(@slice) > 0)
		INSERT INTO @tempTable(ITEMS) VALUES (@slice)

	SET @String = RIGHT(@String,LEN(@String) - @index)
	IF LEN(@String) = 0 BREAK
END
RETURN 
END



GO
/****** Object:  Table [dbo].[Books]    Script Date: 19/01/2015 11:59:07 PM ******/
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
	[Book_Content] [nvarchar](1000) NOT NULL DEFAULT (''),
	[Cat_Id] [char](36) NULL,
	[Book_Language] [varchar](7) NOT NULL,
	[Book_ImageFile] [varchar](255) NOT NULL DEFAULT ('/image/nocover.png'),
	[Book_CreateDate] [datetime] NOT NULL DEFAULT (getdate()),
	[Book_isDeleted] [bit] NOT NULL DEFAULT ((0)),
PRIMARY KEY CLUSTERED 
(
	[Book_ISBN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Categories](
	[Cat_Id] [char](36) NOT NULL DEFAULT (newid()),
	[Cat_Name] [nvarchar](30) NOT NULL,
	[Cat_isDelete] [bit] NOT NULL DEFAULT ((0)),
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
/****** Object:  Table [dbo].[Copies]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Copies](
	[Cop_Id] [char](36) NOT NULL CONSTRAINT [DF__Copies__Cop_Id__2E1BDC42]  DEFAULT (newid()),
	[Cop_No] [char](15) NOT NULL,
	[Book_ISBN] [varchar](13) NULL,
	[Cop_Status] [bit] NOT NULL CONSTRAINT [DF__Copies__Cop_Stat__300424B4]  DEFAULT ((1)),
	[Cop_isDeleted] [bit] NOT NULL CONSTRAINT [DF__Copies__Cop_isDe__30F848ED]  DEFAULT ((0)),
 CONSTRAINT [PK__Copies__D0CC12DCA84EE103] PRIMARY KEY CLUSTERED 
(
	[Cop_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UQ__Copies__D0CC2A0E6D955C4B] UNIQUE NONCLUSTERED 
(
	[Cop_No] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Fines]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Fines](
	[Fine_Id] [char](36) NOT NULL DEFAULT (newid()),
	[IRBookDetail_Id] [char](36) NULL,
	[Fine_Amount] [money] NOT NULL,
	[Fine_CreateDate] [datetime] NOT NULL DEFAULT (getdate()),
	[Fine_PaidDate] [datetime] NULL,
	[Fine_Status] [bit] NOT NULL DEFAULT ((0)),
PRIMARY KEY CLUSTERED 
(
	[Fine_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[IRBookDetails]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[IRBookDetails](
	[IRBookDetail_Id] [char](36) NOT NULL DEFAULT (newid()),
	[IRBook_Id] [char](36) NULL,
	[Cop_Id] [char](36) NULL,
	[IRBookDetail_Status] [bit] NOT NULL DEFAULT ((0)),
	[IRBookDetail_ReturnDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[IRBookDetail_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[IRBooks]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[IRBooks](
	[IRBook_Id] [char](36) NOT NULL DEFAULT (newid()),
	[Mem_Id] [char](36) NULL,
	[IRBook_CreateDate] [datetime] NOT NULL DEFAULT (getdate()),
	[IRBook_DueDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[IRBook_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Members]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Members](
	[Mem_Id] [char](36) NOT NULL DEFAULT (newid()),
	[Mem_FirstName] [nvarchar](30) NOT NULL,
	[Mem_LastName] [nvarchar](50) NOT NULL,
	[Mem_No] [char](8) NOT NULL,
	[Mem_Phone] [varchar](20) NULL,
	[Mem_Address] [nvarchar](200) NULL,
	[Mem_Email] [varchar](50) NOT NULL,
	[Mem_Status] [bit] NOT NULL DEFAULT ((1)),
	[Mem_CreateDate] [datetime] NOT NULL DEFAULT (getdate()),
	[Mem_isDelete] [bit] NOT NULL DEFAULT ((0)),
	[Mem_ImageFile] [varchar](255) NOT NULL DEFAULT ('/image/noavatar.png'),
PRIMARY KEY CLUSTERED 
(
	[Mem_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Mem_No] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Mem_Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Staffs]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Staffs](
	[Staff_Id] [char](36) NOT NULL DEFAULT (newid()),
	[Staff_FirstName] [nvarchar](30) NULL,
	[Staff_LastName] [nvarchar](50) NULL,
	[Staff_Login] [varchar](25) NOT NULL,
	[Staff_Password] [varchar](50) NOT NULL,
	[Staff_Phone] [varchar](20) NULL,
	[Staff_Role] [varchar](15) NOT NULL,
	[Staff_Address] [nvarchar](200) NULL,
	[Staff_Email] [varchar](50) NOT NULL,
	[Staff_Status] [bit] NOT NULL DEFAULT ((1)),
	[Staff_CreateDate] [datetime] NOT NULL DEFAULT (getdate()),
	[Staff_isDelete] [bit] NOT NULL DEFAULT ((0)),
	[Staff_ImageFile] [varchar](255) NOT NULL DEFAULT ('/image/noavatar.png'),
PRIMARY KEY CLUSTERED 
(
	[Staff_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Staff_Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Staff_Login] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  View [dbo].[view_Book_Cate_Copy]    Script Date: 19/01/2015 11:59:07 PM ******/
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
/****** Object:  View [dbo].[view_IR_IRDetail_Member]    Script Date: 19/01/2015 11:59:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[view_IR_IRDetail_Member]
AS
SELECT        IR.IRBook_Id, IR.Mem_Id, M.Mem_FirstName, M.Mem_LastName, M.Mem_No, M.Mem_Phone, M.Mem_Address, M.Mem_Email, M.Mem_Status, M.Mem_CreateDate, M.Mem_isDelete, M.Mem_ImageFile, 
                         IR.IRBook_CreateDate, IR.IRBook_DueDate, IRD.IRBookDetail_Status, IRD.IRBookDetail_ReturnDate, IRD.Cop_Id, IRD.IRBookDetail_Id
FROM            dbo.IRBooks AS IR LEFT OUTER JOIN
                         dbo.Members AS M ON M.Mem_Id = IR.Mem_Id LEFT OUTER JOIN
                         dbo.IRBookDetails AS IRD ON IRD.IRBook_Id = IR.IRBook_Id

GO
ALTER TABLE [dbo].[Books]  WITH CHECK ADD FOREIGN KEY([Cat_Id])
REFERENCES [dbo].[Categories] ([Cat_Id])
GO
ALTER TABLE [dbo].[Copies]  WITH CHECK ADD  CONSTRAINT [FK__Copies__Book_ISB__2F10007B] FOREIGN KEY([Book_ISBN])
REFERENCES [dbo].[Books] ([Book_ISBN])
GO
ALTER TABLE [dbo].[Copies] CHECK CONSTRAINT [FK__Copies__Book_ISB__2F10007B]
GO
ALTER TABLE [dbo].[Fines]  WITH CHECK ADD FOREIGN KEY([IRBookDetail_Id])
REFERENCES [dbo].[IRBookDetails] ([IRBookDetail_Id])
GO
ALTER TABLE [dbo].[IRBookDetails]  WITH CHECK ADD  CONSTRAINT [FK__IRBookDet__Cop_I__3A81B327] FOREIGN KEY([Cop_Id])
REFERENCES [dbo].[Copies] ([Cop_Id])
GO
ALTER TABLE [dbo].[IRBookDetails] CHECK CONSTRAINT [FK__IRBookDet__Cop_I__3A81B327]
GO
ALTER TABLE [dbo].[IRBookDetails]  WITH CHECK ADD FOREIGN KEY([IRBook_Id])
REFERENCES [dbo].[IRBooks] ([IRBook_Id])
GO
ALTER TABLE [dbo].[IRBooks]  WITH CHECK ADD FOREIGN KEY([Mem_Id])
REFERENCES [dbo].[Members] ([Mem_Id])
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "B"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 136
               Right = 217
            End
            DisplayFlags = 280
            TopColumn = 6
         End
         Begin Table = "C"
            Begin Extent = 
               Top = 6
               Left = 255
               Bottom = 136
               Right = 427
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "COP"
            Begin Extent = 
               Top = 6
               Left = 465
               Bottom = 136
               Right = 635
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'view_Book_Cate_Copy'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'view_Book_Cate_Copy'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "IR"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 136
               Right = 227
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "M"
            Begin Extent = 
               Top = 6
               Left = 265
               Bottom = 136
               Right = 445
            End
            DisplayFlags = 280
            TopColumn = 7
         End
         Begin Table = "IRD"
            Begin Extent = 
               Top = 6
               Left = 483
               Bottom = 136
               Right = 703
            End
            DisplayFlags = 280
            TopColumn = 1
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'view_IR_IRDetail_Member'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'view_IR_IRDetail_Member'
GO
