/**
 *
 * Copyright (c) 2006-2015, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.codegen;

import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.MultiGenerator;
import com.speedment.codegen.java.JavaInstaller;
import com.speedment.codegen.jdbc.JDBCInstaller;
import com.speedment.codegen.jdbc.models.Column;
import com.speedment.codegen.jdbc.models.Index;
import com.speedment.codegen.jdbc.models.Table;
import java.sql.JDBCType;

/**
 *
 * @author Emil Forslund
 */
public class Example {
	private Example() {}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		final CodeGenerator cg = new MultiGenerator(
			new JavaInstaller(),
			new JDBCInstaller()
		);
		
		Formatting.tab("    ");
		
		System.out.println(cg.on(new Table("Account")
			.add(new Column("id", JDBCType.INTEGER)
				.add(new Index("PRIMARY", Index.Type.PRIMARY))
			)
			.add(new Column("mail", JDBCType.VARCHAR).setSize(128)
				.add(new Index("mail_Idx", Index.Type.UNIQUE))
			)
			.add(new Column("username", JDBCType.VARCHAR).setSize(32)
				.add(new Index("username_Idx", Index.Type.UNIQUE))
			)
			.add(new Column("password", JDBCType.CHAR).setSize(32)
				.add(new Index("password_Idx", Index.Type.INDEX))
			)
			.add(new Column("salt", JDBCType.CHAR).setSize(32))
		).get());
		
//		final Type typeThread  = new Type(Thread.class);
//		final Type spriteStore = new Type("org.duncan.test.SpriteStore");
//		final Type soundStore  = new Type("org.duncan.test.SoundStore");
//		
//		Enum myEnum;
//		System.out.println(cg.on(myEnum = new Enum("org.duncan.util.Card")
//				.public_()
//					
//				.add(new EnumConstant("HEART_OF_ACE")
//					.add(new NumberValue(1))
//					.add(new TextValue("♥"))
//				)
//				.add(new EnumConstant("HEART_OF_SPADES")
//					.add(new NumberValue(1))
//					.add(new TextValue("♠"))
//				)
//				.add(new EnumConstant("HEART_OF_CLUBS")
//					.add(new NumberValue(1))
//					.add(new TextValue("♣"))
//				)
//				.add(new EnumConstant("HEART_OF_DIAMONDS")
//					.add(new NumberValue(1))
//					.add(new TextValue("♦"))
//				)
//			).get() + "\n"
//		);
//		
//		System.out.println(cg.on(new Interface("org.duncan.test.Player")
//				/***** Class declaration *****/
//				.public_()
//				.setJavadoc(new Javadoc(
//					"This is a test class to demonstrate how the\n" +
//					"code generator is working."
//				))
//					
//				/***** Fields *****/
//				.add(new Field("cards", list(myEnum.asType()))
//					.private_().final_().static_()
//					.setValue(new ReferenceValue("new ArrayList<>()"))
//				)
//				.add(new Field("primitiveCards", myEnum.asType().setArrayDimension(2))
//					.private_().final_().static_()
//					.setValue(new ReferenceValue("new Card() [4][13]"))
//				)
//				.add(new Field("favoriteCard", myEnum.asType())
//					.private_().final_()
//					.setValue(new EnumValue(myEnum.asType(), "HEART_OF_ACE"))
//				)	
//					
//				/***** Methods *****/
//				.add(new Method("spawn", VOID)
//					.setJavadoc(new Javadoc(
//						"This function is used to reset the Player."
//					))
//					.add(new Field("name", STRING))
//					.add(new Field("score", INT_PRIMITIVE))
//				)
//			).get() + "\n"
//		);
//		
//		System.out.println(cg.on(new Class("org.duncan.test.MittTest", typeThread)
//				/***** Dependencies *****/
//				.add(new Import(LIST))
//				
//				/***** Class declaration *****/
//				.public_()
//				.setJavadoc(new Javadoc(
//					"This is a test class to demonstrate how the\n" +
//					"code generator is working."
//				))
//				
//				/***** Fields *****/
//				.add(new Field("player1Name", STRING))
//				.add(new Field("player2Name", STRING))
//				.add(new Field("player1Score", INT_PRIMITIVE))
//				.add(new Field("player2Score", INT_PRIMITIVE))
//				.add(new Field("players", list(new Type("org.duncan.test.Player"))))
//				.add(new Field("spriteStore", spriteStore))
//				.add(new Field("soundStore", soundStore))
//					
//				/***** Methods *****/
//				.add(new Method("spawnPlayer1", VOID).public_()
//					.setJavadoc(new Javadoc(
//						"This function is used to reset Player 1."
//					))
//					.add(new Field("name", STRING))
//					.add(new Field("score", INT_PRIMITIVE))
//					.add("this.player1Name = name;")
//					.add("this.player1Score = score;")
//				)
//				.add(new Method("spawnPlayer2", VOID).public_()
//					.setJavadoc(new Javadoc(
//						"This function is used to reset Player 2."
//					))
//					.add(new Field("name", STRING))
//					.add(new Field("score", INT_PRIMITIVE))
//					.add("this.player2Name = name;")
//					.add("this.player2Score = score;")
//				)
//				.add(new Method("killPlayer", VOID).public_()
//					.setJavadoc(new Javadoc(
//						"This method can be used to kill either player."
//					))
//					.add(new Field("name", STRING))
//					.add("switch (name) " + block(
//						"case (this.player1Name) : killPlayer1(); break;",
//						"case (this.player2Name) : killPlayer2(); break;"
//					))
//				)
//				.add(new Method("killPlayer1", VOID).protected_()
//					.add("this.player2Score += 20;")
//				)
//				.add(new Method("killPlayer2", VOID).protected_()
//					.add("this.player1Score += 20;")
//				)
//				.add(new Method("restart", VOID).public_()
//					.add("spawnPlayer1();")
//					.add("spawnPlayer2();")
//				)
//				
//				/***** Controllers *****/
//				.call(new SetGet())
//				.call(new FinalParameters())
//				.call(new AutoJavadoc())
//					
//			
//			).get() + "\n"
//		);
	}
	
}