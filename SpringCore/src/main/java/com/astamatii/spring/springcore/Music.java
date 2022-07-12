package com.astamatii.spring.springcore;

/**
 * <style>
 * table, th {
 * border: 1px solid black;
 * border-collapse: collapse;
 * padding-left: 10px;
 * padding-right: 10px;
 * text-align: left;}
 * </style>
 * 
 * <p>The <i>Music</i> interface contains the following methods:</p>
 * 
 * <p><table>
 * <tr> <th><b>Method</b> </th> <th><b>Description</b> </th></tr>
 * <tr> <th><i>doMyInit()</i> </th> <th>The public method applied after object is initiated. The <i>init-method</i> for beans </th></tr>
 * <tr> <th><i>doMyDestroy()</i> </th> <th>The public method that destroys the object. The <i>destroy-method</i> for beans </th></tr>
 * <tr> <th><i>getSong()</i> </th> <th>The public method used to print the music track on the screen </th></tr>
 * </table></p> 
 * 
 * @author Alexandr Stamatii
 *
 */
public interface Music {
	void doMyInit();
	void doMyDestroy();
	String getSong();	
}
