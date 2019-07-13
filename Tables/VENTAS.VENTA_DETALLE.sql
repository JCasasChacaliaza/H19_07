CREATE TABLE [VENTAS].[VENTA_DETALLE]
(
[CODVENTDET] [int] NOT NULL IDENTITY(1, 1),
[CODEQU] [int] NULL,
[CODVEN] [int] NULL,
[FEHCVENDET] [date] NULL
) ON [PRIMARY]
GO
ALTER TABLE [VENTAS].[VENTA_DETALLE] ADD CONSTRAINT [PK__VENTA_DE__005A9EEEDDAD67CB] PRIMARY KEY CLUSTERED  ([CODVENTDET]) ON [PRIMARY]
GO
ALTER TABLE [VENTAS].[VENTA_DETALLE] ADD CONSTRAINT [EQUIPOS_VENTA_DETALLE_CODVEN] FOREIGN KEY ([CODEQU]) REFERENCES [MAESTRA].[EQUIPOS] ([CODEQU])
GO
ALTER TABLE [VENTAS].[VENTA_DETALLE] ADD CONSTRAINT [VENTA_VENTA_DETALLE_CODVEN] FOREIGN KEY ([CODVEN]) REFERENCES [VENTAS].[VENTA] ([CODVEN])
GO
