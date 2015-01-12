USE [LMS]
GO
/****** Object:  View [dbo].[view_Book]    Script Date: 01/09/2015 17:02:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[view_Book]
AS
SELECT     dbo.Books.Book_ISBN, dbo.Books.Book_Title, dbo.Books.Book_Publisher, dbo.Books.Book_Author, dbo.Books.Book_Price, dbo.Books.Book_Content, dbo.Books.Book_Language, 
                      dbo.Books.Book_ImageFile, dbo.Books.Book_CreateDate, dbo.Copies.Cop_No, dbo.Copies.Cop_Status, dbo.Categories.Cat_Name, dbo.Categories.Cat_Description, dbo.Copies.Cop_Id
FROM         dbo.Books INNER JOIN
                      dbo.Copies ON dbo.Books.Book_ISBN = dbo.Copies.Book_ISBN INNER JOIN
                      dbo.Categories ON dbo.Books.Cat_Id = dbo.Categories.Cat_Id
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
         Begin Table = "Books"
            Begin Extent = 
               Top = 158
               Left = 260
               Bottom = 278
               Right = 434
            End
            DisplayFlags = 280
            TopColumn = 7
         End
         Begin Table = "Copies"
            Begin Extent = 
               Top = 154
               Left = 495
               Bottom = 274
               Right = 655
            End
            DisplayFlags = 280
            TopColumn = 1
         End
         Begin Table = "Categories"
            Begin Extent = 
               Top = 156
               Left = 37
               Bottom = 276
               Right = 202
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
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'view_Book'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'view_Book'
GO
/****** Object:  View [dbo].[view_IRBook]    Script Date: 01/09/2015 17:02:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[view_IRBook]
AS
SELECT     dbo.IRBooks.IRBook_Id, dbo.IRBooks.Mem_Id, dbo.IRBooks.IRBook_CreateDate, dbo.IRBooks.IRBook_DueDate, dbo.IRBookDetails.IRBookDetail_Id, dbo.IRBookDetails.Cop_Id, 
                      dbo.IRBookDetails.IRBookDetail_Status, dbo.IRBookDetails.IRBookDetail_ReturnDate
FROM         dbo.IRBookDetails INNER JOIN
                      dbo.IRBooks ON dbo.IRBookDetails.IRBook_Id = dbo.IRBooks.IRBook_Id
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
         Begin Table = "IRBookDetails"
            Begin Extent = 
               Top = 153
               Left = 292
               Bottom = 273
               Right = 504
            End
            DisplayFlags = 280
            TopColumn = 1
         End
         Begin Table = "IRBooks"
            Begin Extent = 
               Top = 155
               Left = 48
               Bottom = 275
               Right = 233
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
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'view_IRBook'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'view_IRBook'
GO
/****** Object:  StoredProcedure [dbo].[IRBook_Issue]    Script Date: 01/09/2015 17:02:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		TRUNGHTH
-- Create date: 09/01/2015
-- Description:	ISSUE BOOK
-- =============================================
CREATE PROCEDURE [dbo].[IRBook_Issue]
@Cop_Id char(36)
,@Mem_Id char(36)	
AS

DECLARE @IRBook_Id char(36)
DECLARE @CountTemp int
DECLARE @ReturnValue int
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
				   
				--EXEC @ReturnValue = IRBookDetails_Insert @IRBook_Id,@Cop_Id	
				--INSERT IRBookDetails	
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
/****** Object:  StoredProcedure [dbo].[IRBook_Return]    Script Date: 01/09/2015 17:02:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		TRUNGHTH
-- Create date: 09/01/2015
-- Description:	ISSUE BOOK
-- =============================================
CREATE PROCEDURE [dbo].[IRBook_Return]
@IRBookDetail_Id  char(36)
,@Fine_Amount decimal(7,2)
,@lateday int
AS

DECLARE @IRBook_Id char(36)
DECLARE @CountTemp int
DECLARE @ReturnValue int
DECLARE @IRBook_DueDate datetime
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
/****** Object:  StoredProcedure [dbo].[Fine_Paid]    Script Date: 01/09/2015 17:02:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[Fine_Paid]
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
