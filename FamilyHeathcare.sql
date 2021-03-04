USE [master]
GO
/****** Object:  Database [FamilyHealthcare]    Script Date: 27/2/2021 10:02:45 AM ******/
CREATE DATABASE [FamilyHealthcare]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'FamilyHealthcare', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\FamilyHealthcare.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'FamilyHealthcare_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\FamilyHealthcare_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [FamilyHealthcare] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [FamilyHealthcare].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [FamilyHealthcare] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [FamilyHealthcare] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [FamilyHealthcare] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [FamilyHealthcare] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [FamilyHealthcare] SET ARITHABORT OFF 
GO
ALTER DATABASE [FamilyHealthcare] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [FamilyHealthcare] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [FamilyHealthcare] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [FamilyHealthcare] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [FamilyHealthcare] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [FamilyHealthcare] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [FamilyHealthcare] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [FamilyHealthcare] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [FamilyHealthcare] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [FamilyHealthcare] SET  DISABLE_BROKER 
GO
ALTER DATABASE [FamilyHealthcare] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [FamilyHealthcare] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [FamilyHealthcare] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [FamilyHealthcare] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [FamilyHealthcare] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [FamilyHealthcare] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [FamilyHealthcare] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [FamilyHealthcare] SET RECOVERY FULL 
GO
ALTER DATABASE [FamilyHealthcare] SET  MULTI_USER 
GO
ALTER DATABASE [FamilyHealthcare] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [FamilyHealthcare] SET DB_CHAINING OFF 
GO
ALTER DATABASE [FamilyHealthcare] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [FamilyHealthcare] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [FamilyHealthcare] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'FamilyHealthcare', N'ON'
GO
USE [FamilyHealthcare]
GO
/****** Object:  Table [dbo].[Registrations]    Script Date: 27/2/2021 10:02:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Registrations](
	[RegistrationID] [nvarchar](10) NOT NULL,
	[Fullname] [nvarchar](50) NULL,
	[Phone] [nvarchar](15) NULL,
	[Email] [nvarchar](30) NULL,
	[Address] [nvarchar](50) NULL,
	[Age] [int] NULL,
	[Gender] [bit] NULL,
	[NumberOfMember] [int] NULL,
	[NumberOfChildren] [int] NULL,
	[NumberOfAdults] [int] NULL,
 CONSTRAINT [PK_Registrations] PRIMARY KEY CLUSTERED 
(
	[RegistrationID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
USE [master]
GO
ALTER DATABASE [FamilyHealthcare] SET  READ_WRITE 
GO
