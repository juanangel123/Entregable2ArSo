package es.um.arso.buscador.util;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * Copyright 2005 Joe Walker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 
 * @author Abey Mullassery
 * 
 */

public class PrintDOM {

  public static void printTree(Node doc) {
    if (doc == null) {
      System.out.println("Nothing to print!!");
      return;
    }
    try {
      System.out.println(doc.getNodeName() + "  " + doc.getNodeValue());
      NamedNodeMap cl = doc.getAttributes();
      for (int i = 0; i < cl.getLength(); i++) {
        Node node = cl.item(i);
        System.out.println(
          "\t" + node.getNodeName() + " -> " + node.getNodeValue());
      }
      NodeList nl = doc.getChildNodes();
      for (int i = 0; i < nl.getLength(); i++) {
        Node node = nl.item(i);
        printTree(node);
      }
    } catch (Throwable e) {
      System.out.println("Cannot print!! " + e.getMessage());
    }
  }

}
