/**
 * Copyright 2008 code_swarm project team
 *
 * This file is part of code_swarm.
 *
 * code_swarm is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * code_swarm is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with code_swarm.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.vecmath.Vector2f;

/**
 * Abstract class describing interface of any code_swarm physical engine.
 *
 * @note Need to be derived to define force calculation algorithms between Nodes
 * @note Need to use the constructor to apply some configuration options
 * 
 * @note For portability, no Processing library should be use there, only standard Java packages
 */
abstract class PhysicalEngine
{
  private static CodeSwarmConfig cfg;
  
  public PhysicalEngine(){
    try {
      cfg = new CodeSwarmConfig("physics_engine/Legacy.config");
    } catch (IOException e) {
      System.err.println("Failed due to exception: " + e.getMessage());
    }
  };
  /**
   * Method that calculate the attractive/repulsive force between a person and one of its file along their link (the edge).
   * 
   * @param edge the link between a person and one of its file 
   * @return force force calculated between those two nodes
   */
  abstract public Vector2f calculateForceAlongAnEdge( code_swarm.Edge edge );

  /**
   * Method that calculate the force between to nodes.
   * 
   * @param nodeA [in]
   * @param nodeB [in]
   * @return force force calculated between those two nodes
   */
  abstract Vector2f calculateForceBetweenNodes( code_swarm.Node nodeA, code_swarm.Node nodeB );

  /**
   * Method that apply a force to a node, converting force to acceleration, that in turn modify speed.
   * 
   * @param node [in] Node the node to which the force apply
   * @param force [in] a force Vector representing the force on a node
   * 
   * @Note Standard physics is "Speed Variation = Force / Mass x Duration" with a convention of "Duration=1" between to frames
   */
  abstract void applyForceTo( code_swarm.Node node, Vector2f force );

  /**
   * Method that manage speed conversion to position.
   * 
   * @param node [in] the node to which the force apply
   * 
   * @Note Standard physics is "Position Variation = Speed x Duration" with a convention of "Duration=1" between to frames
   */
  abstract void applySpeedTo( code_swarm.Node node );
  
  /**
   * Method that allows Physics Engine to modify Speed / Position during the relax phase.
   * 
   * @param nodeList the list of People
   * @param node the node to which the force apply
   * 
   * @Note Standard physics is "Position Variation = Speed x Duration" with a convention of "Duration=1" between to frames
   */
  abstract void onRelaxNode( CopyOnWriteArrayList<code_swarm.FileNode> nodeList, code_swarm.FileNode node );
  
  /**
   * Method that allows Physics Engine to modify Speed / Position during the relax phase.
   * 
   * @param nodeList the list of People
   * @param node the node to which the force apply
   * 
   * @Note Standard physics is "Position Variation = Speed x Duration" with a convention of "Duration=1" between to frames
   */
  abstract void onRelaxPerson( CopyOnWriteArrayList<code_swarm.PersonNode> nodeList, code_swarm.PersonNode node );
  
  /**
   * Method that allows Physics Engine to modify Speed / Position during the update phase.
   * 
   * @param nodeList the list of People
   * @param node the node to which the force apply
   * 
   * @Note Standard physics is "Position Variation = Speed x Duration" with a convention of "Duration=1" between to frames
   */
  abstract void onUpdateNode( CopyOnWriteArrayList<code_swarm.FileNode> nodeList, code_swarm.FileNode node );
  
  /**
   * Method that allows Physics Engine to modify Speed / Position during the update phase.
   * 
   * @param nodeList the list of People
   * @param node the node to which the force apply
   * 
   * @Note Standard physics is "Position Variation = Speed x Duration" with a convention of "Duration=1" between to frames
   */
  abstract void onUpdatePerson( CopyOnWriteArrayList<code_swarm.PersonNode> nodeList, code_swarm.PersonNode node );
}
