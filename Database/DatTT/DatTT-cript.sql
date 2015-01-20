USE [LMS]
GO
/****** Object:  Table [dbo].[Books]    Script Date: 1/20/2015 1:31:56 PM ******/
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
/****** Object:  Table [dbo].[Categories]    Script Date: 1/20/2015 1:31:56 PM ******/
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
/****** Object:  Table [dbo].[Copies]    Script Date: 1/20/2015 1:31:56 PM ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Fines]    Script Date: 1/20/2015 1:31:56 PM ******/
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
/****** Object:  Table [dbo].[IRBookDetails]    Script Date: 1/20/2015 1:31:56 PM ******/
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
/****** Object:  Table [dbo].[IRBooks]    Script Date: 1/20/2015 1:31:56 PM ******/
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
PRIMARY KEY CLUSTERED 
(
	[IRBook_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Members]    Script Date: 1/20/2015 1:31:56 PM ******/
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
/****** Object:  Table [dbo].[Staffs]    Script Date: 1/20/2015 1:31:56 PM ******/
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
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9780316373807', N'See How Small', N'Little Brown and Company', N'Scott Blackwood', 11.9900, N'A riveting novel about the aftermath of a brutal murder of three teenage girls written in incantatory prose thats as fine as any being written by an American author today. 
', N'40929920-5B3D-4071-BE2B-5FAC464A48CB', N'English', N'imgBook/20150120105738_seehowsmallanovel.jpg', CAST(0x0000A42600B4A0AE AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9780345539816', N'Golden Son ', N'Del Rey ', N'Pierce Brown ', 19.0000, N'With shades of The Hunger Games Enders Game and Game of Thrones debut author Pierce Browns genre-defying epic Red Rising hit the ground running and wasted no time becoming a sensation.
', N'8CD5271F-CAC4-4D23-A199-4AEF5A47D113', N'Russian', N'imgBook/20150120110654_GoldenSon.jpg', CAST(0x0000A42600B72C15 AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9780544309876', N'Black River ', N'Houghton Mifflin Harcourt ', N'S. M. Hulse ', 15.9900, N'A tense Western and an assured debut Black River tells the story of a man marked by a prison riot as he returns to the town and the convict who shaped him.
', N'4FE6CDBA-49A4-4A6D-B719-7DAAE3599304', N'French', N'imgBook/20150120110305_BlackRiver.jpg', CAST(0x0000A42600B61F65 AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9780670785957', N'West of Sunset', N'Viking Adult', N'Stewart ONan', 11.9900, N'A rich sometimes heartbreaking (Dennis Lehane novel of F. Scott Fitzgeralds last years in Hollywood
', N'4FE6CDBA-49A4-4A6D-B719-7DAAE3599304', N'English', N'imgBook/20150120105942_WestofSunset.jpg', CAST(0x0000A42600B53209 AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9780770433246', N'It Was Me All Along', N'Clarkson Potter', N'Andie Mitchell', 14.4000, N'A heartbreakingly honest endearing memoir of incredible weight loss by a young food blogger who battles body image issues and overcomes food addiction to find self-acceptance.
', N'8C3AB2C7-5DE4-458E-83DD-A721792D5429', N'German', N'imgBook/20150120110551_ItWasMeAllAlong.jpg', CAST(0x0000A42600B6E285 AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9780871407900', N'Sweetland A Novel', N'Liveright', N'Michael Crummey', 17.9700, N'The epic tale of an endangered Newfoundland community and the struggles of one man determined to resist its extinction
', N'4FE6CDBA-49A4-4A6D-B719-7DAAE3599304', N'German', N'imgBook/20150120110128_SweetlandANovel.jpg', CAST(0x0000A42600B5ADDF AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9781439172568', N'The First Bad Man ', N'Scribner ', N'Miranda July ', 15.0000, N'From the acclaimed filmmaker artist and bestselling author of No One Belongs Here More Than You a spectacular debut novel that is so heartbreaking so dirty so tender so funny—so Miranda July—that readers will be blown away.
', N'7ED34419-10EE-40FC-81AD-B35108D3B506', N'English', N'imgBook/20150120110448_TheFirstBadMan.jpg', CAST(0x0000A42600B698CB AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9781455551927', N'The Secret Wisdom of the Earth', N'Grand Central Publishing', N'Christopher Scotton', 11.0000, N'After witnessing the death of his younger brother in a terrible home accident 14-year-old Kevin and his grieving mother are sent for the summer to live with Kevins grandfather', N'BBACEBF6-096B-4B9A-823A-941BB5F17AA9', N'Vietnam', N'imgBook/20150120110741_TheSecretWisdomoftheEarth.jpg', CAST(0x0000A42600B76300 AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9781484205396', N'Beginning Xcode', N'Apress', N'Matthew Knott', 11.0000, N'Beginning Xcode Swift Edition will not only get you up and running with Apples latest version of Xcode but it also shows you how to use Swift in Xcode and includes a variety of projects to build.
', N'EA3D573E-EBD6-4DBE-A5CB-36DAB14F9EF9', N'English', N'imgBook/20150120110825_beginningxcode.jpg', CAST(0x0000A42600B796F5 AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9781594205866', N'Leaving Before the Rains Come', N'Penguin Press HC The', N'Alexandra Fuller', 16.2000, N'...one of the gutsiest memoirs Ive ever read. And the writing--oh my god the writing. --Entertainment Weekly', N'4FE6CDBA-49A4-4A6D-B719-7DAAE3599304', N'French', N'imgBook/20150120110217_LeavingBeforetheRainsCome.jpg', CAST(0x0000A42600B5E762 AS DateTime), 0)
INSERT [dbo].[Books] ([Book_ISBN], [Book_Title], [Book_Publisher], [Book_Author], [Book_Price], [Book_Content], [Cat_Id], [Book_Language], [Book_ImageFile], [Book_CreateDate], [Book_isDeleted]) VALUES (N'9781594633669', N'The Girl on the Train', N'Riverhead Hardcover', N'Riverhead Hardcover', 16.1700, N'Gone Girl fans will devour this psychological thriller.—People 
', N'4FE6CDBA-49A4-4A6D-B719-7DAAE3599304', N'Spanish', N'imgBook/20150120110406_TheGirlontheTrain.jpg', CAST(0x0000A42600B666F2 AS DateTime), 0)
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'40929920-5B3D-4071-BE2B-5FAC464A48CB', N'Education', 0, N'Education')
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'4FE6CDBA-49A4-4A6D-B719-7DAAE3599304', N'Novels', 0, N'Novels')
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'7ED34419-10EE-40FC-81AD-B35108D3B506', N'Comics & Graphic ', 0, N'Comics & Graphic ')
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'8C3AB2C7-5DE4-458E-83DD-A721792D5429', N'Health, Fitness', 0, N'Health, Fitness')
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'8CD5271F-CAC4-4D23-A199-4AEF5A47D113', N'Teen & Young ', 0, N'Teen & Young ')
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'BBACEBF6-096B-4B9A-823A-941BB5F17AA9', N'Travel', 0, N'Travel')
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'EA164A6E-3939-4D1A-A704-2B1A3B5AB65E', N'Romance', 0, N'Romance')
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'EA3D573E-EBD6-4DBE-A5CB-36DAB14F9EF9', N'Computers & Technology', 0, N'Computers & Technology')
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'EDA38AE3-47F6-41FB-8194-515F56C6F8E6', N'Medical Books', 0, N'Medical Books')
INSERT [dbo].[Categories] ([Cat_Id], [Cat_Name], [Cat_isDelete], [Cat_Description]) VALUES (N'FE232B72-C46B-4242-80D8-30A6E8F58F62', N'History', 0, N'History')
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'02A9CC68-3491-4595-9D5A-FDAD84E11B89', N'150120110129003', N'9780871407900', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'07568609-69C8-4F5E-8AC0-8D531349BDA9', N'150120110655003', N'9780345539816', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'11F77D4C-ECC5-4C21-87EA-41FE6265601E', N'150120110742002', N'9781455551927', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'16CD46BD-EA79-45BB-AEC8-AC350FEFFFFC', N'150120110826001', N'9781484205396', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'20AB67DB-0017-4DEA-9945-2DCB14A2448C', N'150120110407001', N'9781594633669', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'3691D39A-597A-4645-A4AB-BE7FF59EB949', N'150120110826002', N'9781484205396', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'3CE0F5C0-4B5C-4D9F-9FF8-F1FC2BD5A582', N'150120110553003', N'9780770433246', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'46EFCAD9-54BD-4E66-B05F-2964CF518771', N'150120110129001', N'9780871407900', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'5EE7F447-463C-4142-AA45-555848B9948B', N'150120105943002', N'9780670785957', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'5F531F7A-0529-483E-950D-04449B5291D0', N'150120110306003', N'9780544309876', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'610A18D0-1C43-45B4-9B05-53339D306273', N'150120110742001', N'9781455551927', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'67A92F16-0C93-46B0-AA6D-DC27B666D402', N'150120110306001', N'9780544309876', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'699D018D-24D6-4ED0-87A1-405CC50A73D4', N'150120110218002', N'9781594205866', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'6D19212A-94BE-4A9D-AEEA-A094E16A2398', N'150120110218003', N'9781594205866', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'6E66DDA8-7A69-41AA-86D1-9AEF1E97EB73', N'150120110655001', N'9780345539816', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'7294C57C-50E3-4589-BB1B-7262C086F003', N'150120110450001', N'9781439172568', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'7B2F9162-8A04-4DB0-A133-B79464D60DD3', N'150120110407003', N'9781594633669', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'89E59C08-E193-4846-A32E-33C30AC73B14', N'150120105740003', N'9780316373807', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'9D9DDED7-CA25-4001-9EA3-DFB8B3938444', N'150120110742003', N'9781455551927', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'A318CA87-04F7-48A6-BB32-AD941879AD8C', N'150120110218001', N'9781594205866', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'A4994ED2-4C22-493F-BBE4-C2028A5D4E50', N'150120105943003', N'9780670785957', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'A6DAAB37-DBCB-422B-9BEC-2E30190D8097', N'150120110129002', N'9780871407900', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'CB226701-0C34-4ADD-B238-FB53D0C183E7', N'150120110553001', N'9780770433246', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'CBB78F93-0108-4F78-8F85-E097ECB96237', N'150120110450002', N'9781439172568', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'CFFC74D0-854D-49E1-8250-CA3B797E956B', N'150120110306002', N'9780544309876', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'D2940EC3-4127-4B0C-A63A-677D957EE062', N'150120110827003', N'9781484205396', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'DBF3BD9A-04A6-49DE-941D-8E486284D960', N'150120110553002', N'9780770433246', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'F056F4F7-0C92-40ED-95B2-EADCBA51FCE9', N'150120105740001', N'9780316373807', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'F08445F3-58E2-4640-898C-7334268359B7', N'150120105943001', N'9780670785957', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'F4AAA6FB-BC2D-41FF-AC90-CEE5C16B3938', N'150120110450003', N'9781439172568', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'F8271603-50AB-445B-853E-6189BE741501', N'150120110655002', N'9780345539816', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'FA7BC850-8B9E-4729-8FA5-4FCF869967FE', N'150120110407002', N'9781594633669', 1, 0)
INSERT [dbo].[Copies] ([Cop_Id], [Cop_No], [Book_ISBN], [Cop_Status], [Cop_isDeleted]) VALUES (N'FC3A555E-AEB6-464F-9CDE-249BD4BC10B1', N'150120105740002', N'9780316373807', 1, 0)
INSERT [dbo].[Members] ([Mem_Id], [Mem_FirstName], [Mem_LastName], [Mem_No], [Mem_Phone], [Mem_Address], [Mem_Email], [Mem_Status], [Mem_CreateDate], [Mem_isDelete], [Mem_ImageFile]) VALUES (N'1BB59517-83C1-444E-B330-273662591B6B', N'HUYNH NHAT LINH ', N'VY', N'M0000005', N'0906574839', N'4 UT TICH', N'VYHNL@YAHOO.COM', 1, CAST(0x0000A42600DCE1B1 AS DateTime), 0, N'imgMem/MemNoAvatar.png')
INSERT [dbo].[Members] ([Mem_Id], [Mem_FirstName], [Mem_LastName], [Mem_No], [Mem_Phone], [Mem_Address], [Mem_Email], [Mem_Status], [Mem_CreateDate], [Mem_isDelete], [Mem_ImageFile]) VALUES (N'280E7F4D-E76B-400D-9BEE-09099B4C1CE9', N'NGUYEN THI MY ', N'HOA', N'M0000003', N'0123456789', N'8 NGUYEN HONG DAO', N'HOANTM@GMAIL.COM', 1, CAST(0x0000A42600DBE3C4 AS DateTime), 0, N'imgMem/MemNoAvatar.png')
INSERT [dbo].[Members] ([Mem_Id], [Mem_FirstName], [Mem_LastName], [Mem_No], [Mem_Phone], [Mem_Address], [Mem_Email], [Mem_Status], [Mem_CreateDate], [Mem_isDelete], [Mem_ImageFile]) VALUES (N'C4C2FB68-015A-4B5F-AF5D-6154A1B8B78E', N'NGUYEN QUOC ', N'HUY', N'M0000002', N'0901732812', N'1 DONG DEN', N'HUYNQ@GMAIL.COM', 1, CAST(0x0000A42600DBA31E AS DateTime), 0, N'imgMem/MemNoAvatar.png')
INSERT [dbo].[Members] ([Mem_Id], [Mem_FirstName], [Mem_LastName], [Mem_No], [Mem_Phone], [Mem_Address], [Mem_Email], [Mem_Status], [Mem_CreateDate], [Mem_isDelete], [Mem_ImageFile]) VALUES (N'CCAB1772-53BC-4EA9-AA84-49D15B4BE3A6', N'NGUYEN DUY ', N'THAI', N'M0000001', N'', N'3 TRUONG CHINH', N'THAIND@GMAIL.COM', 1, CAST(0x0000A42600DB3EDF AS DateTime), 0, N'imgMem/MemNoAvatar.png')
INSERT [dbo].[Members] ([Mem_Id], [Mem_FirstName], [Mem_LastName], [Mem_No], [Mem_Phone], [Mem_Address], [Mem_Email], [Mem_Status], [Mem_CreateDate], [Mem_isDelete], [Mem_ImageFile]) VALUES (N'EA74D49C-848A-4D03-8101-74B652E1603B', N'PHAM QUYNH ', N'ANH', N'M0000004', N'0987654321', N'7 CONG HOA ', N'ANHPQ@GMAIL.COM', 1, CAST(0x0000A42600DC25A8 AS DateTime), 0, N'imgMem/MemNoAvatar.png')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'1A00F8E8-397E-44A9-BFF8-C0E430DA3BC9', N'PHAM TAM ', N'ANH', N'ANHPT', N'ANH', N'', N'Book Manage', N'1 NGUYEN HIEN LE', N'ANHPT@GMAIL.COM', 1, CAST(0x0000A42600D95709 AS DateTime), 0, N'')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'25915311-CEC8-41B1-85E1-A649BF5370DA', N'TO THANH ', N'DAT', N'DATTT', N'ASD', N'', N'Admin', N'212  NGUYEN HUU TIEN', N'TOTHANHDAT.244@GMAIL.COM', 1, CAST(0x0000A42600D6FC02 AS DateTime), 0, N'')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'2D590D62-DCDC-4282-B46A-E1BD8DE15555', N'DAO DUY ', N'KHANG', N'KHANGDD', N'KHANG', N'', N'Fine Manage', N'56 CACH MANG THANG TAM	', N'KHANGDD@GMAIL.COM', 1, CAST(0x0000A42600DA9F31 AS DateTime), 0, N'')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'36F06B5F-F9A6-4925-A5A3-4F95197B0ED0', N'HO THI NGOC ', N'THUY', N'KIMNANA', N'NANA', N'', N'Member Manage', N'214 NGUYEN HUU TIEN', N'GAUMEO@GMAIL.COM', 1, CAST(0x0000A42600D86EE8 AS DateTime), 0, N'')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'4656C00F-C500-4D19-A087-905B1880EB73', N'DAO NGOC', N'ANH', N'ANHDN', N'ANHDN', N'', N'Fine Manage', N'4 LY THUONG KIET', N'ANHDN@FPT.EDU.VN', 1, CAST(0x0000A42600DAE294 AS DateTime), 0, N'')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'744F924A-35EB-4A71-8D89-4BDFE8D372D4', N'TRA PHUC VINH', N'UY', N'UYTPV', N'UY', N'', N'Book Manage', N'123 HONG BANG	', N'UYTPV', 1, CAST(0x0000A42600D8ADB9 AS DateTime), 0, N'')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'835909B8-6226-4C1E-8ECA-04E6C84C238B', N'NGUYEN MINH ', N'SON', N'SONNM', N'SON', N'', N'IR Manage', N'22 NGUYEN HIEN LE	', N'SONNM@GMAIL.COM', 1, CAST(0x0000A42600D9BA96 AS DateTime), 0, N'')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'A4AB548E-1608-4F46-9C7E-776D97F534CE', N'LE QUOC', N'HUNG', N'HUNGLQ', N'HUNG', N'', N'Member Manage', N'333 NAM KI KHOI NGHIA	', N'HUNGLQ@GMAIL.COM', 1, CAST(0x0000A42600D8F2FE AS DateTime), 0, N'')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'AB18E2FF-E946-4AAD-80D8-856030D5530B', N'GIANG TUAN', N'VY', N'VYGT', N'VY', N'', N'IR Manage', N'44 VUON LAI', N'VYGT@GMAIL.COM', 1, CAST(0x0000A42600DA41DA AS DateTime), 0, N'')
INSERT [dbo].[Staffs] ([Staff_Id], [Staff_FirstName], [Staff_LastName], [Staff_Login], [Staff_Password], [Staff_Phone], [Staff_Role], [Staff_Address], [Staff_Email], [Staff_Status], [Staff_CreateDate], [Staff_isDelete], [Staff_ImageFile]) VALUES (N'E22BB752-CF49-441D-9FAC-53AE75AD76DA', N'HUA TRAN HUU', N'TRUNG', N'TRUNGHTH', N'ABC@123', N'0934399664', N'Admin', N'195B Q.10 TPHCM', N'TRUNGHTH@GMAIL.COM', 1, CAST(0x0000A42600C2202C AS DateTime), 0, N'imgStaff/anh (7).jpg')
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__Categori__B46D3EC31ED998B2]    Script Date: 1/20/2015 1:31:56 PM ******/
ALTER TABLE [dbo].[Categories] ADD UNIQUE NONCLUSTERED 
(
	[Cat_Name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__Copies__D0CC2A0E300424B4]    Script Date: 1/20/2015 1:31:56 PM ******/
ALTER TABLE [dbo].[Copies] ADD UNIQUE NONCLUSTERED 
(
	[Cop_No] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__Members__816F186D0519C6AF]    Script Date: 1/20/2015 1:31:56 PM ******/
ALTER TABLE [dbo].[Members] ADD UNIQUE NONCLUSTERED 
(
	[Mem_No] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__Members__84ACAA1A023D5A04]    Script Date: 1/20/2015 1:31:56 PM ******/
ALTER TABLE [dbo].[Members] ADD UNIQUE NONCLUSTERED 
(
	[Mem_Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__Staffs__56F4EFF2108B795B]    Script Date: 1/20/2015 1:31:56 PM ******/
ALTER TABLE [dbo].[Staffs] ADD UNIQUE NONCLUSTERED 
(
	[Staff_Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__Staffs__E00644CE1367E606]    Script Date: 1/20/2015 1:31:56 PM ******/
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
ALTER TABLE [dbo].[Copies]  WITH CHECK ADD FOREIGN KEY([Book_ISBN])
REFERENCES [dbo].[Books] ([Book_ISBN])
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
ALTER TABLE [dbo].[IRBooks]  WITH CHECK ADD FOREIGN KEY([Mem_Id])
REFERENCES [dbo].[Members] ([Mem_Id])
GO
