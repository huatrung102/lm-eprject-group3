USE [LMS]
GO
/****** Object:  StoredProcedure [dbo].[Books_getByISBN]    Script Date: 15/01/2015 6:04:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		trunghth
-- Create date: <Create Date,,>
-- Description:	get Books by ISBN
-- =============================================
CREATE PROCEDURE [dbo].[Books_getByISBN]
@Book_ISBN varchar(13)

AS
BEGIN
	SELECT B.Book_Author
	,B.Book_ISBN
	,B.Book_ImageFile
	,B.Book_Language
	,B.Book_Publisher
	,B.Book_Title
	,BCC.Cat_Name
				,COUNT(BCC.Cop_Id) 'Book_Count'
	FROM Books B LEFT JOIN view_Book_Cate_Copy BCC
		ON B.Book_ISBN = BCC.Book_ISBN
	WHERE 
			B.Book_ISBN = @Book_ISBN AND
			B.Book_isDeleted = 0
	GROUP BY B.Book_Author,B.Book_ISBN,B.Book_ImageFile,B.Book_Language,B.Book_Publisher,B.Book_Title,BCC.Cat_Name
END

GO
/****** Object:  StoredProcedure [dbo].[Books_getListBookByFilter]    Script Date: 15/01/2015 6:04:42 PM ******/
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
,@Cat_Id char(36) = ''
AS
BEGIN
		SELECT 
					B.[Book_ISBN] 'ISBN'
					,B.[Book_Title] 'Title'
					,B.Book_Author 'Author'
		FROM view_Book_Cate_Copy B
		
		WHERE B.[Book_ISBN] LIKE (CASE WHEN @Book_ISBN = '' THEN B.[Book_ISBN] ELSE '%'+@Book_ISBN+'%' END)
			AND	B.Book_Title LIKE (CASE WHEN @Book_Title = '' THEN B.Book_Title ELSE '%'+@Book_Title+'%' END)
			AND	B.Cat_Id = (CASE WHEN @Cat_Id = '' THEN B.Cat_Id ELSE @Cat_Id END)
		group by B.[Book_ISBN] 
					,B.[Book_Title] 
					,B.Book_Author 

END

GO
/****** Object:  StoredProcedure [dbo].[Copies_getLastestIsFree]    Script Date: 15/01/2015 6:04:42 PM ******/
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
/****** Object:  StoredProcedure [dbo].[Fines_Paid]    Script Date: 15/01/2015 6:04:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		trunghth
-- Create date: <Create Date,,>
-- Description:	paid fee fine
-- =============================================
create PROCEDURE [dbo].[Fines_Paid]
@Fine_Id char(36)
AS
BEGIN
	BEGIN TRAN
		BEGIN TRY			
			UPDATE Fines
				SET Fine_PaidDate = GETDATE()
					,Fine_Status = 1
			WHERE @Fine_Id = Fine_Id AND Fine_Status = 0
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
/****** Object:  StoredProcedure [dbo].[IRBooks_getListBookNotReturnByMemberNo]    Script Date: 15/01/2015 6:04:42 PM ******/
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
	1 as ' '
     ,Row_number() over (order by [IRBook_CreateDate]),
      [IRBook_CreateDate]
      ,[IRBook_DueDate]
		,BCC.Book_ISBN
		,BCC.Book_Title
		,BCC.Cop_No
		
	   FROM [view_IR_IRDetail_Member] IRM 
	   LEFT JOIN [view_Book_Cate_Copy] BCC ON IRM.Cop_Id = BCC.Cop_Id
	   WHERE	IRM.IRBookDetail_Status = 0 
	   
END

GO
/****** Object:  StoredProcedure [dbo].[IRBooks_IssueBook]    Script Date: 15/01/2015 6:04:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		TRUNGHTH
-- Create date: 09/01/2015
-- Description:	ISSUE BOOK
-- =============================================
Create PROCEDURE [dbo].[IRBooks_IssueBook]
@Cop_Id char(36)
,@Mem_Id char(36)	
AS

DECLARE @IRBook_Id char(36)
DECLARE @CountTemp int

BEGIN
	SELECT @CountTemp = COUNT(*) 
			FROM IRBooks IR LEFT JOIN
				IRBookDetails IRD ON IR.IRBook_Id = IRD.IRBook_Id
			WHERE IRD.IRBookDetail_Status = 0
			GROUP BY IR.MEM_ID
	IF @CountTemp < 5	--KIEM TRA CO VUOT QUA SO LUONG MUON CHO PHEP HAY CHUA
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
			   ,[IRBookDetail_Status] )			   
				VALUES
			   (NEWID()
			   ,@IRBook_Id
			   ,@Cop_Id
			   ,0
			   )
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
/****** Object:  StoredProcedure [dbo].[IRBooks_ReturnBook]    Script Date: 15/01/2015 6:04:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		TRUNGHTH
-- Create date: 09/01/2015
-- Description:	Return BOOK
-- =============================================
Create PROCEDURE [dbo].[IRBooks_ReturnBook]
@IRBookDetail_Id  char(36)
,@Fine_Amount decimal(7,2)
,@lateday int
AS
BEGIN
	IF EXISTS (SELECT IRBookDetail_Id
			FROM IRBookDetails 
			WHERE IRBookDetail_Id = @IRBookDetail_Id and IRBookDetail_Status = 0)			
	--KIEM TRA CO TON TAI RECORD CHUA TRA SACH HAY KHONG??
	BEGIN		
		BEGIN TRAN
			BEGIN TRY			
			UPDATE 	IRBookDetails
				SET IRBookDetail_Status = 1
					,IRBookDetail_ReturnDate = GETDATE()
			if @lateday  > 0
			INSERT INTO Fines (IRBookDetail_Id,Fine_Amount)	
						VALUES(@IRBookDetail_Id, @Fine_Amount) 		 
						
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
		RETURN 0; --ERROR KHONG TON TAI RECORD TREN
		
END

GO
/****** Object:  StoredProcedure [dbo].[Members_GetByParameters]    Script Date: 15/01/2015 6:04:42 PM ******/
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
  AND (Mem_FirstName LIKE (CASE WHEN @Mem_name  = '' THEN Mem_FirstName ELSE @Mem_name END) 
			OR	Mem_LastName  LIKE (CASE WHEN @Mem_name  = '' THEN Mem_LastName ELSE @Mem_name END))
  AND Mem_Email = (CASE WHEN @Mem_Email  = '' THEN Mem_Email ELSE @Mem_Email END)
  AND Mem_Phone = (CASE WHEN @Mem_Phone  = '' THEN Mem_Phone ELSE @Mem_Phone END)
END

GO
/****** Object:  StoredProcedure [dbo].[Members_GetIRCountInformation]    Script Date: 15/01/2015 6:04:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		TRUNGHTH
-- Create date: <Create Date,,>
-- Description:	GET MEMBER INFORMATION WITH BOOK ISSUED
-- =============================================
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
	  ,[Book Issued]
	   FROM [dbo].[Members] MEM INNER JOIN 
		  (	  SELECT M.[Mem_Id]
						,COUNT(Cop_Id) 'Book Issued'
			FROM [dbo].[Members] M
			LEFT JOIN IRBooks IR ON IR.Mem_Id = M.Mem_Id
			LEFT JOIN IRBookDetails IRD ON IRD.IRBook_Id = IR.IRBook_Id 
			WHERE IRD.IRBookDetail_Status = 0
			GROUP BY M.[Mem_Id]) TEMP

		ON TEMP.Mem_Id = MEM.Mem_Id
END

GO
/****** Object:  UserDefinedFunction [dbo].[Split]    Script Date: 15/01/2015 6:04:42 PM ******/
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
RETURNS @tempTable TABLE (items varchar(50)) 
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
/****** Object:  View [dbo].[view_Book_Cate_Copy]    Script Date: 15/01/2015 6:04:42 PM ******/
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
/****** Object:  View [dbo].[view_IR_IRDetail_Member]    Script Date: 15/01/2015 6:04:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[view_IR_IRDetail_Member]
AS
SELECT        IR.IRBook_Id, IR.Mem_Id, M.Mem_FirstName, M.Mem_LastName, M.Mem_No, M.Mem_Phone, M.Mem_Address, M.Mem_Email, M.Mem_Status, M.Mem_CreateDate, M.Mem_isDelete, M.Mem_ImageFile, 
                         IR.IRBook_CreateDate, IR.IRBook_DueDate, IRD.IRBookDetail_Status, IRD.IRBookDetail_ReturnDate, IRD.Cop_Id
FROM            dbo.IRBooks AS IR LEFT OUTER JOIN
                         dbo.Members AS M ON M.Mem_Id = IR.Mem_Id LEFT OUTER JOIN
                         dbo.IRBookDetails AS IRD ON IRD.IRBook_Id = IR.IRBook_Id

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
