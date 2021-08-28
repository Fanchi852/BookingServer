/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author Fanchi
 */
public interface IDAO <B,M> {

    public Integer add (B bean);
    public Boolean deleteAll ();
    public Boolean delete (B bean);
    public List<B> findAll ();
    public List<B> find (B bean);
    public List findBy (M filter);
    public Boolean update (B bean);
	

}
