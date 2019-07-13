CREATE TABLE [VENTAS].[VENTA]
(
[CODVEN] [int] NOT NULL IDENTITY(1, 1),
[IDPER] [int] NULL,
[CANTVEN] [int] NULL
) ON [PRIMARY]
GO
ALTER TABLE [VENTAS].[VENTA] ADD CONSTRAINT [PK__VENTA__CB9EAF0F9B4FAB2B] PRIMARY KEY CLUSTERED  ([CODVEN]) ON [PRIMARY]
GO
ALTER TABLE [VENTAS].[VENTA] ADD CONSTRAINT [VENTA_PERSONA_IDPER] FOREIGN KEY ([IDPER]) REFERENCES [MAESTRA].[PERSONA] ([IDPER])
GO
