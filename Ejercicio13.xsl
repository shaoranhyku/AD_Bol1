<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:template match="/">
		<html>
			<xsl:apply-templates />
		</html>
	</xsl:template>

	<xsl:template match="ListaContactos">
		<head><title>LISTADO DE ALUMNOS</title></head>
		<body>
			<hl>LISTA DE ALUMNOS</hl>
			<table border="l">
				<tr>
					<th>Nombre</th>
					<th>Número de teléfono</th>
					<th>Dirección</th>
					<th>Código postal</th>
					<th>Fecha de nacimiento</th>
					<th>¿Deudas?</th>
					<th>Cantidad</th>
				</tr>
				<xsl:apply-templates select="Contacto"/>
			</table>
		</body>

	</xsl:template>
	<xsl:template match="Contacto">
		<tr><xsl:apply-templates /></tr>
	</xsl:template>

	<xsl:template match="name|phoneNumber|address|postalCode|birthdate|hasDebts|debts">
		<td><xsl:apply-templates /></td>
	</xsl:template>
</xsl:stylesheet>