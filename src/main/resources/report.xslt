<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:common="http://exslt.org/common"
                exclude-result-prefixes="common">
    <xsl:template match="/report">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="first"
                                       margin-right="1in"
                                       margin-left="1in"
                                       margin-bottom="1in"
                                       margin-top="1in"
                                       page-width="11in"
                                       page-height="8.26in">
                    <fo:region-body margin-top="0.5in" margin-bottom="0.5in"/>
                    <fo:region-before extent="0.5in"/>
                    <fo:region-after extent="0.5in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="first">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block line-height="14pt" font-size="14pt" font-weight="bold"
                              text-align="center"><xsl:value-of select="title/text()"/>
                    </fo:block>
                </fo:static-content>
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block line-height="14pt" font-size="10pt"
                              text-align="end">Page
                        <fo:page-number/>
                    </fo:block>
                </fo:static-content>

                <fo:flow flow-name="xsl-region-body">
                    <xsl:if test="/report">
                        <fo:table table-layout="fixed" width="100%" padding-start="10pt" padding-end="10pt"
                                  padding-before="10pt" padding-after="10pt">
                            <xsl:for-each select="/report/headers/header">
                                <fo:table-column column-width="proportional-column-width(1)"/>
                            </xsl:for-each>
                            <fo:table-body>
                                <fo:table-row>
                                    <xsl:for-each select="/report/headers/header">
                                        <fo:table-cell background-color="gray" border-style="solid" border-color="black"
                                                       border-width="1pt" padding-start="1pt" padding-end="1pt"
                                                       padding-before="1pt" padding-after="1pt">
                                            <fo:block font-weight="bold" text-align="center">
                                                <xsl:value-of select="text()"/>
                                            </fo:block>
                                        </fo:table-cell>
                                    </xsl:for-each>
                                </fo:table-row>
                                <xsl:for-each select="/report/data/row">
                                    <fo:table-row>
                                        <xsl:for-each select="value">
                                            <fo:table-cell border-style="solid" border-color="black" border-width="1pt"
                                                           padding-start="1pt" padding-end="1pt" padding-before="1pt"
                                                           padding-after="1pt">
                                                <fo:block text-align="center">
                                                    <xsl:value-of select="text()"/>
                                                </fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>
                                    </fo:table-row>
                                </xsl:for-each>
                            </fo:table-body>
                        </fo:table>
                    </xsl:if>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>



