USE [LMS]
GO
/****** Object:  UserDefinedFunction [dbo].[returnNextMemNo]    Script Date: 1/20/2015 12:23:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
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
/****** Object:  Table [dbo].[Books]    Script Date: 1/20/2015 12:23:53 PM ******/
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
	[Book_Language] [varchar](7) NOT NULL,
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
/****** Object:  Table [dbo].[Categories]    Script Date: 1/20/2015 12:23:54 PM ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Copies]    Script Date: 1/20/2015 12:23:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Copies](
	[Cop_Id] [char](36) NOT NULL,
	[Cop_No] [char](8) NOT NULL,
	[Book_ISBN] [varchar](13) NULL,
	[Cop_Status] [bit] NOT NULL,
	[Cop_isDeleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Cop_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Fines]    Script Date: 1/20/2015 12:23:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Fines](
	[Fine_Id] [char](36) NOT NULL,
	[IRBookDetail_Id] [char](36) NULL,
	[Fine_Amount] [money] NOT NULL,
	[Fine_CreateDate] [datetime] NOT NULL,
	[Fine_PaidDate] [datetime] NULL,
	[Fine_Status] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Fine_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[IRBookDetails]    Script Date: 1/20/2015 12:23:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[IRBookDetails](
	[IRBookDetail_Id] [char](36) NOT NULL,
	[IRBook_Id] [char](36) NULL,
	[Cop_Id] [char](36) NULL,
	[IRBookDetail_Status] [bit] NOT NULL,
	[IRBookDetail_ReturnDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[IRBookDetail_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[IRBooks]    Script Date: 1/20/2015 12:23:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[IRBooks](
	[IRBook_Id] [char](36) NOT NULL,
	[Mem_Id] [char](36) NULL,
	[IRBook_CreateDate] [datetime] NOT NULL,
	[IRBook_DueDate] [datetime] NOT NULL,
	[IRBook_Description] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[IRBook_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Members]    Script Date: 1/20/2015 12:23:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Members](
	[Mem_Id] [char](36) NOT NULL,
	[Mem_FirstName] [nvarchar](30) NOT NULL,
	[Mem_LastName] [nvarchar](50) NOT NULL,
	[Mem_No] [char](8) NOT NULL,
	[Mem_Phone] [varchar](20) NULL,
	[Mem_Address] [nvarchar](200) NULL,
	[Mem_Email] [varchar](50) NOT NULL,
	[Mem_Status] [bit] NOT NULL,
	[Mem_CreateDate] [datetime] NOT NULL,
	[Mem_isDelete] [bit] NOT NULL,
	[Mem_ImageFile] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Mem_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Staffs]    Script Date: 1/20/2015 12:23:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Staffs](
	[Staff_Id] [char](36) NOT NULL,
	[Staff_FirstName] [nvarchar](30) NULL,
	[Staff_LastName] [nvarchar](50) NULL,
	[Staff_Login] [varchar](25) NOT NULL,
	[Staff_Password] [varchar](50) NOT NULL,
	[Staff_Phone] [varchar](20) NULL,
	[Staff_Role] [varchar](15) NOT NULL,
	[Staff_Address] [nvarchar](200) NULL,
	[Staff_Email] [varchar](50) NOT NULL,
	[Staff_Status] [bit] NOT NULL,
	[Staff_CreateDate] [datetime] NOT NULL,
	[Staff_isDelete] [bit] NOT NULL,
	[Staff_ImageFile] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Staff_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Members] ([Mem_Id], [Mem_FirstName], [Mem_LastName], [Mem_No], [Mem_Phone], [Mem_Address], [Mem_Email], [Mem_Status], [Mem_CreateDate], [Mem_isDelete], [Mem_ImageFile]) VALUES (N'07B76D1C-D776-4DAD-9331-F1D1FD15E617', N'H?a Tr?n H?u', N'Trung', N'M0000003', N'0122987432', N'293 Ð?ng Ðen', N'trunghth@fpt.edu.vn', 1, CAST(0x0000A42600A875AE AS DateTime), 0, N'imgMem/MemNoAvatar.png')
INSERT [dbo].[Members] ([Mem_Id], [Mem_FirstName], [Mem_LastName], [Mem_No], [Mem_Phone], [Mem_Address], [Mem_Email], [Mem_Status], [Mem_CreateDate], [Mem_isDelete], [Mem_ImageFile]) VALUES (N'5245A187-F3C4-47A1-8C31-64B9C1103CDA', N'Le Hong ', N'Ky', N'M0000005', N'', N'20 H?ng Bàng', N'kylh@gmail.com', 1, CAST(0x0000A42600AC899F AS DateTime), 0, N'imgMem/MemNoAvatar.png')
INSERT [dbo].[Members] ([Mem_Id], [Mem_FirstName], [Mem_LastName], [Mem_No], [Mem_Phone], [Mem_Address], [Mem_Email], [Mem_Status], [Mem_CreateDate], [Mem_isDelete], [Mem_ImageFile]) VALUES (N'asdsad                              ', N'Trà Phúc Vinh', N'Uy', N'M0000002', N'0122894390', N'21/20 Vu?n Lài', N'pigbanguy@gmail.com', 1, CAST(0x0000917300000000 AS DateTime), 0, N'/image/noavatar.png')
INSERT [dbo].[Members] ([Mem_Id], [Mem_FirstName], [Mem_LastName], [Mem_No], [Mem_Phone], [Mem_Address], [Mem_Email], [Mem_Status], [Mem_CreateDate], [Mem_isDelete], [Mem_ImageFile]) VALUES (N'D16443B7-EDD7-4BF4-94CB-0139209A0876', N'Ho Thi Ngoc ', N'Thuy', N'M0000006', N'', N'212 Nguy?n H?u Ti?n', N'gaumeo@gmail.com', 1, CAST(0x0000A42600CBDE90 AS DateTime), 0, N'imgMem/MemNoAvatar.png')
INSERT [dbo].[Members] ([Mem_Id], [Mem_FirstName], [Mem_LastName], [Mem_No], [Mem_Phone], [Mem_Address], [Mem_Email], [Mem_Status], [Mem_CreateDate], [Mem_isDelete], [Mem_ImageFile]) VALUES (N'EDEDD907-DAF6-4208-8257-529F18FD7571', N'Le Quoc ', N'Hung', N'M0000004', N'0167928392', N'28 C?ng Hòa', N'hunglq@gmail.com', 1, CAST(0x0000A42600A9B24A AS DateTime), 0, N'imgMem/MemNoAvatar.png')
INSERT [dbo].[Members] ([Mem_Id], [Mem_FirstName], [Mem_LastName], [Mem_No], [Mem_Phone], [Mem_Address], [Mem_Email], [Mem_Status], [Mem_CreateDate], [Mem_isDelete], [Mem_ImageFile]) VALUES (N'zxcasdqweasdzxcasdqweasdzxcasdqweqwe', N'Tô Thành', N'Ð?t', N'M0000001', N'09876543210', N'21/12 Vu?n Lài', N'tothanhdat.244@gmail.com', 1, CAST(0x0000902500000000 AS DateTime), 0, N'/image/noavatar.png')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'33146047-237D-47BF-B338-1EF01E80FCFF', N'Ðào Duy', N'Khang', N'khangdd', N'khang', N'0192323811', N'Member Manage', N'23/5 C?ng Hòa', N'khangdd@gmail.com', 1, CAST(0x0000A42600A6EF54 AS DateTime), 0, N'')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'4166ABF4-D242-4FAB-AC1B-3D6B488D93B6', N'Nguy?n Minh ', N'Son', N'sonnm', N'son', N'0123984121', N'Member Manage', N'23/5 Nguy?n H?ng Ðào		', N'sonnm@fpt.edu.vn', 1, CAST(0x0000A42600A6ACC2 AS DateTime), 0, N'')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'5DCB3D14-69FF-4C81-B4D1-B21F1D1D06D0', N'Nguy?n Tâm', N'Anh', N'anhnt', N'anh', N'0123456789', N'IR Manage', N'31/3 Lê Tr?ng T?n', N'anhnt@gmail.com', 1, CAST(0x0000A42600A6217C AS DateTime), 0, N'')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'83B15795-251D-4896-A98A-B0536FA597FD', N'Giang Tu?n', N'V?', N'vygt', N'vy', N'0123498982', N'IR Manage', N'23/33 B?ch Ð?ng', N'vygt@yahoo.com', 1, CAST(0x0000A42600A75B53 AS DateTime), 0, N'')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'88E27D42-0AF0-4DD3-9970-AA8DBAE495CC', N'Lê H?ng ', N'K?', N'kylh', N'ky', N'0192389293', N'Fine Manage', N'22/5 Tru?ng Chinh	', N'kylh@gmail.com', 1, CAST(0x0000A42600A7DE32 AS DateTime), 0, N'')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'CB37CCAE-E98F-42DE-8DA7-BFCCDCBFFE6F', N'Ðào Ng?c', N'Anh', N'anhdn', N'anh', N'0938452342', N'Fine Manage', N'23/9 Cách M?ng Tháng Tám	', N'anhdn@gmail.com', 1, CAST(0x0000A42600A79304 AS DateTime), 0, N'')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'E46B528F-87C9-4C04-B321-977DB28F7345', N'Lê Qu?c ', N'Hung', N'hunglq', N'hung', N'0909246357', N'Book Manage', N'12/1 Nam Kì Kh?i Nghia ', N'hunglq@gmail.com', 1, CAST(0x0000A42600A4CCBF AS DateTime), 0, N'')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'EE12C0F7-4325-4265-982F-494B0BB96B84', N'Trà Phúc Vinh ', N'Uy', N'uytpv', N'uy', N'0909123456', N'Book Manage', N'21/4 Khánh H?i', N'uytpv@gmai.com', 1, CAST(0x0000A42600A5D9D9 AS DateTime), 0, N'')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'M0000001                            ', N'Tô Thành', N'Đạt', N'dattt', N'abc', N'0123456789', N'Staff', N'21/20 Vườn Lài', N'tothanhdat.244@gmail.com', 1, CAST(0x0000A41E013CFB73 AS DateTime), 0, N'/image/noavatar.png')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'M0000002                            ', N'Hứa Trần Hữu', N'Trung', N'trungHTH', N'123', N'01234567891', N'Book', N'21/20 Vườn Lài , Phú Thọ Hòa, Tân Phú,thành phố hồ chí minh việt nam', N'trungHTH@gmail.com', 1, CAST(0x0000A420010BF7A1 AS DateTime), 0, N'/image/noavatar.png')
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__Categori__B46D3EC37E3C4148]    Script Date: 1/20/2015 12:23:54 PM ******/
ALTER TABLE [dbo].[Categories] ADD UNIQUE NONCLUSTERED 
(
	[Cat_Name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__Copies__D0CC2A0E56721B07]    Script Date: 1/20/2015 12:23:54 PM ******/
ALTER TABLE [dbo].[Copies] ADD UNIQUE NONCLUSTERED 
(
	[Cop_No] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__Members__816F186D7BEAA540]    Script Date: 1/20/2015 12:23:54 PM ******/
ALTER TABLE [dbo].[Members] ADD UNIQUE NONCLUSTERED 
(
	[Mem_No] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__Members__84ACAA1A187A3CB2]    Script Date: 1/20/2015 12:23:54 PM ******/
ALTER TABLE [dbo].[Members] ADD UNIQUE NONCLUSTERED 
(
	[Mem_Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__Staffs__56F4EFF25E15A02E]    Script Date: 1/20/2015 12:23:54 PM ******/
ALTER TABLE [dbo].[Staffs] ADD UNIQUE NONCLUSTERED 
(
	[Staff_Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__Staffs__E00644CEAB611F92]    Script Date: 1/20/2015 12:23:54 PM ******/
ALTER TABLE [dbo].[Staffs] ADD UNIQUE NONCLUSTERED 
(
	[Staff_Login] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
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
ALTER TABLE [dbo].[Fines] ADD  DEFAULT (newid()) FOR [Fine_Id]
GO
ALTER TABLE [dbo].[Fines] ADD  DEFAULT (getdate()) FOR [Fine_CreateDate]
GO
ALTER TABLE [dbo].[Fines] ADD  DEFAULT ((0)) FOR [Fine_Status]
GO
ALTER TABLE [dbo].[IRBookDetails] ADD  DEFAULT (newid()) FOR [IRBookDetail_Id]
GO
ALTER TABLE [dbo].[IRBookDetails] ADD  DEFAULT ((0)) FOR [IRBookDetail_Status]
GO
ALTER TABLE [dbo].[IRBooks] ADD  DEFAULT (newid()) FOR [IRBook_Id]
GO
ALTER TABLE [dbo].[IRBooks] ADD  DEFAULT (getdate()) FOR [IRBook_CreateDate]
GO
ALTER TABLE [dbo].[Members] ADD  DEFAULT (newid()) FOR [Mem_Id]
GO
ALTER TABLE [dbo].[Members] ADD  DEFAULT ((1)) FOR [Mem_Status]
GO
ALTER TABLE [dbo].[Members] ADD  DEFAULT (getdate()) FOR [Mem_CreateDate]
GO
ALTER TABLE [dbo].[Members] ADD  DEFAULT ((0)) FOR [Mem_isDelete]
GO
ALTER TABLE [dbo].[Members] ADD  DEFAULT ('/image/noavatar.png') FOR [Mem_ImageFile]
GO
ALTER TABLE [dbo].[Staffs] ADD  DEFAULT (newid()) FOR [Staff_Id]
GO
ALTER TABLE [dbo].[Staffs] ADD  DEFAULT ((1)) FOR [Staff_Status]
GO
ALTER TABLE [dbo].[Staffs] ADD  DEFAULT (getdate()) FOR [Staff_CreateDate]
GO
ALTER TABLE [dbo].[Staffs] ADD  DEFAULT ((0)) FOR [Staff_isDelete]
GO
ALTER TABLE [dbo].[Staffs] ADD  DEFAULT ('/image/noavatar.png') FOR [Staff_ImageFile]
GO
ALTER TABLE [dbo].[Books]  WITH CHECK ADD FOREIGN KEY([Cat_Id])
REFERENCES [dbo].[Categories] ([Cat_Id])
GO
ALTER TABLE [dbo].[Books]  WITH CHECK ADD FOREIGN KEY([Cat_Id])
REFERENCES [dbo].[Categories] ([Cat_Id])
GO
ALTER TABLE [dbo].[Copies]  WITH CHECK ADD FOREIGN KEY([Book_ISBN])
REFERENCES [dbo].[Books] ([Book_ISBN])
GO
ALTER TABLE [dbo].[Fines]  WITH CHECK ADD FOREIGN KEY([IRBookDetail_Id])
REFERENCES [dbo].[IRBookDetails] ([IRBookDetail_Id])
GO
ALTER TABLE [dbo].[Fines]  WITH CHECK ADD FOREIGN KEY([IRBookDetail_Id])
REFERENCES [dbo].[IRBookDetails] ([IRBookDetail_Id])
GO
ALTER TABLE [dbo].[IRBookDetails]  WITH CHECK ADD FOREIGN KEY([Cop_Id])
REFERENCES [dbo].[Copies] ([Cop_Id])
GO
ALTER TABLE [dbo].[IRBookDetails]  WITH CHECK ADD FOREIGN KEY([IRBook_Id])
REFERENCES [dbo].[IRBooks] ([IRBook_Id])
GO
ALTER TABLE [dbo].[IRBookDetails]  WITH CHECK ADD FOREIGN KEY([IRBook_Id])
REFERENCES [dbo].[IRBooks] ([IRBook_Id])
GO
ALTER TABLE [dbo].[IRBooks]  WITH CHECK ADD FOREIGN KEY([Mem_Id])
REFERENCES [dbo].[Members] ([Mem_Id])
GO
ALTER TABLE [dbo].[IRBooks]  WITH CHECK ADD FOREIGN KEY([Mem_Id])
REFERENCES [dbo].[Members] ([Mem_Id])
GO
