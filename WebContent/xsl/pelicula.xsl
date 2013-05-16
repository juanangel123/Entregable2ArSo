<?xml version="1.0" ?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:as="http://server.arso.um.es/">

	<xsl:output indent="yes" media-type="text/html" />

	<xsl:template match="as:pelicula">
		<html>
			<head>
				<title>
					Pelicula -
					<xsl:value-of select="@titulo"></xsl:value-of>
				</title>
				<!-- Esto es un problema, porque cuando se intenta acceder a estos archivos 
					y se hace una peticion desde REST, el path cambia (de ahi la ruta absoluta, 
					aunque sea mediante "internet") -->
				<link href="http://localhost:8080/Entregable2/css/reset.css"
					rel="stylesheet" type="text/css" />
				<link href="http://localhost:8080/Entregable2/css/common.css"
					rel="stylesheet" type="text/css" />
				<link
					href="http://localhost:8080/Entregable2/css/jquery-ui-1.8.4.custom.css"
					rel="stylesheet" type="text/css" />
				<link href="http://localhost:8080/Entregable2/css/movie.css" rel="stylesheet" type="text/css" />

			</head>
			<body>
				<div class="titulo ui-state-default">
					<xsl:value-of select="@titulo" />
					<div class="subtitulo">Descripción de la película</div>
				</div>
				<xsl:apply-templates />
			</body>
		</html>
	</xsl:template>

	<xsl:template match="descripcionPelicula">
		<div class="movie-image-panel">
			<div class="movie-poster">
				<div>
					<xsl:param name="image">
						<xsl:value-of select="imagen" />
					</xsl:param>
					<xsl:param name="image_full">
						<xsl:call-template name="obtener_image_full">
							<xsl:with-param name="image">
								<xsl:value-of select="$image" />
							</xsl:with-param>
							<xsl:with-param name="id">
								<xsl:value-of select="../@id" />
							</xsl:with-param>
						</xsl:call-template>
					</xsl:param>
					<a href="{$image}">
						<img src="{$image_full}" />
					</a>
				</div>
			</div>

			<div class="movie-rating-vote">
				<br />
				<br />
				<div class="content">
					<div class="movie-rating-general">
						<div class="movie-rating">
							Puntuacion:
							<xsl:value-of select="puntuacion" />
						</div>
						<div class="count">
							<xsl:value-of select="votos" />
							votos
						</div>
					</div>
				</div>
			</div>
			<div class="float-separator"></div>
		</div>

		<div class="under-poster">
			<div class="float-separator"></div>
		</div>

		<ul class="movie-info">
			<li>
				<span>TITULO
					ORIGINAL
				</span>
				<div>
					<xsl:value-of select="tituloOriginal" />
				</div>
			</li>
			<li>
				<span>AÑO
					/ PAIS
				</span>
				<div>
					<xsl:value-of select="ano" />
					/
					<xsl:value-of select="pais" />
				</div>
			</li>
			<li>
				<span>ESTRENO</span>
				<div>
					<xsl:value-of select="estreno" />
				</div>
			</li>
			<li>
				<span>DURACION
				</span>
				<div>
					<xsl:value-of select="duracion" />
					min.
				</div>
			</li>
			<xsl:for-each select="director">
				<li>
					<span>DIRECTOR</span>
					<div>
						<xsl:value-of select="." />
					</div>
				</li>
			</xsl:for-each>
			<xsl:for-each select="reparto">
				<li>
					<span>REPARTO</span>
					<div>
						<xsl:value-of select="." />
					</div>
				</li>
			</xsl:for-each>
			<xsl:for-each select="guion">
				<li>
					<span>GUION
					</span>
					<div>
						<xsl:value-of select="." />
					</div>
				</li>
			</xsl:for-each>
			<xsl:for-each select="musica">
				<li>
					<span>MUSICA
					</span>
					<div>
						<xsl:value-of select="." />
					</div>
				</li>
			</xsl:for-each>
			<xsl:for-each select="fotografia">
				<li>
					<span>FOTOGRAFIA
					</span>
					<div>
						<xsl:value-of select="." />
					</div>
				</li>
			</xsl:for-each>
			<xsl:for-each select="productora">
				<li>
					<span>PRODUCTORA</span>
					<div>
						<xsl:value-of select="." />
					</div>
				</li>
			</xsl:for-each>
			<xsl:for-each select="genero">
				<li>
					<span>GENERO
					</span>
					<div>
						<xsl:value-of select="." />
					</div>
				</li>
			</xsl:for-each>
			<li>
				<span>PREMIOS</span>
				<div>
					<xsl:value-of select="premios" />
				</div>
			</li>
			<li>
				<span>SINOPSIS</span>
				<div class="movieSynopsis">
					<xsl:value-of select="sinopsis" />
				</div>
			</li>
		</ul>
		<hr />
	</xsl:template>

	<xsl:template match="criticasPrensa">
		<div class="pro-reviews">
			<div class="titulo ui-state-default">
				<div class="subtitulo">Críticas prensa</div>
			</div>

			<ul>
				<xsl:for-each select="criticaPrensa">
					<li>
						<xsl:param name="urlCritica">
							<xsl:value-of select="urlCritica"></xsl:value-of>
						</xsl:param>
						<div class="content">
							<div class="synopsis">
								<xsl:value-of select="sinopsis"></xsl:value-of>
							</div>
							<div class="critic">
								<div>
									<xsl:value-of select="critico"></xsl:value-of>
								</div>
								<div class="media">
									<xsl:value-of select="media"></xsl:value-of>
									<a href="{$urlCritica}" target='blank'>
										<div class="goto">
											<em>Leer crítica entera</em>
										</div>
									</a>
								</div>
							</div>
						</div>
					</li>
				</xsl:for-each>
			</ul>
			<div class="clearfix"></div>
		</div>
	</xsl:template>

	<xsl:template match="criticasUsuario">
		<div class="titulo ui-state-default">
			<div class="subtitulo">Críticas usuario</div>
		</div>
		<xsl:for-each select="criticaUsuario">
			<div class="user-review-card">
				<div>
					<ul class="user-review-info">
						<li class="title">
							<xsl:value-of select="titulo" />
						</li>
						<li>
							por
							<span class="user">
								<xsl:value-of select="usuario" />
							</span>
						</li>
						<li>
							<div>
								Puntuacion:
								<xsl:value-of select="puntuacion" />
							</div>
						</li>
						<li>
							<xsl:param name="urlCriticaUsuario">
								<xsl:value-of select="concat('http://m.filmaffinity.com', urlCritica)" />
							</xsl:param>
							<div>
								<a style="text-decoration: underline;" href="{$urlCriticaUsuario}"
									target='blank'>Leer crítica</a>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="peliculaAmazon">
		<div class="titulo ui-state-default">
			<div class="subtitulo">Pelicula Amazon</div>
		</div>
		<ul class="movie-info">
			<li>
				<span>ASIN</span>
				<div>
					<xsl:value-of select="@ASIN" />
				</div>
			</li>
			<li>
				<span>Titulo</span>
				<div>
					<xsl:value-of select="@title" />
				</div>
			</li>
			<xsl:apply-templates select="descripcionPeliculaAmazon" />
		</ul>
	</xsl:template>

	<xsl:template match="descripcionPeliculaAmazon">
		<li>
			<span>URL Pagina</span>
			<div>
				<xsl:value-of select="detailPageURL" />
			</div>
		</li>
		<li>
			<span>Binding</span>
			<div>
				<xsl:value-of select="binding" />
			</div>
		</li>
		<li>
			<span>EAN</span>
			<div>
				<xsl:value-of select="EAN" />
			</div>
		</li>
		<li>
			<span>Release Date</span>
			<div>
				<xsl:value-of select="releaseDate" />
			</div>
		</li>
		<li>
			<span>Studio</span>
			<div>
				<xsl:value-of select="studio" />
			</div>
		</li>
		<li>
			<span>Numero de Discos</span>
			<div>
				<xsl:value-of select="numberOfDiscs" />
			</div>
		</li>
		<li>
			<span>Precio</span>
			<div>
				<xsl:value-of select="price/formattedPrice" />
			</div>
		</li>
		<xsl:for-each select="format">
			<li>
				<span>Formato</span>
				<div>
					<xsl:value-of select="."></xsl:value-of>
				</div>
			</li>
		</xsl:for-each>
		<xsl:for-each select="languages/language">
			<li>
				<span>Idioma</span>
				<div>
					<xsl:value-of select="name"></xsl:value-of>
					-
					<xsl:value-of select="type"></xsl:value-of>
				</div>
			</li>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="libroAmazon">
		<div class="titulo ui-state-default">
			<div class="subtitulo">Libro Amazon</div>
		</div>
		<ul class="movie-info">
			<li>
				<span>ASIN</span>
				<div>
					<xsl:value-of select="@ASIN" />
				</div>
			</li>
			<li>
				<span>Titulo</span>
				<div>
					<xsl:value-of select="@title" />
				</div>
			</li>
			<xsl:apply-templates select="descripcionLibroAmazon" />
		</ul>
	</xsl:template>

	<xsl:template match="descripcionLibroAmazon">
		<li>
			<span>URL Pagina</span>
			<div>
				<xsl:value-of select="detailPageURL" />
			</div>

		</li>
		<li>
			<span>Binding</span>
			<div>
				<xsl:value-of select="binding" />
			</div>

		</li>
		<li>
			<span>Release Date</span>
			<div>
				<xsl:value-of select="releaseDate" />
			</div>

		</li>
		<li>
			<span>EAN</span>
			<div>
				<xsl:value-of select="EAN" />
			</div>
		</li>
		<xsl:for-each select="author">
			<li>
				<span>Autor</span>
				<div>
					<xsl:value-of select="."></xsl:value-of>
				</div>
			</li>
		</xsl:for-each>
		<li>
			<span>ISBN</span>
			<div>
				<xsl:value-of select="ISBN" />
			</div>
		</li>
		<li>
			<span>Numero de paginas</span>
			<div>
				<xsl:value-of select="numberOfPages" />
			</div>
		</li>
		<li>
			<span>Publicador</span>
			<div>
				<xsl:value-of select="publisher" />
			</div>
		</li>
		<li>
			<span>Precio</span>
			<div>
				<xsl:value-of select="price/formattedPrice" />
			</div>
		</li>
		<xsl:for-each select="format">
			<li>
				<span>Formato</span>
				<div>
					<xsl:value-of select="."></xsl:value-of>
				</div>
			</li>
		</xsl:for-each>
		<xsl:for-each select="languages/language">
			<li>
				<span>Idioma</span>
				<div>
					<xsl:value-of select="name"></xsl:value-of>
					-
					<xsl:value-of select="type"></xsl:value-of>
				</div>
			</li>
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="obtener_image_full">
		<xsl:param name="image" />
		<xsl:param name="id" />
		<xsl:variable name="image_translate">
			<xsl:value-of select="translate($image, '0123456789' , ';;;;' )"></xsl:value-of>
		</xsl:variable>
		<xsl:variable name="image_sub">
			<xsl:value-of select="substring-before($image_translate, ';')"></xsl:value-of>
		</xsl:variable>
		<xsl:value-of select="concat($image_sub, $id, '-full.jpg')" />
	</xsl:template>

</xsl:stylesheet>