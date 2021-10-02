package app.usecase.products.bondMaster;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import project.infra.rdb.bondmaster.BondMaster;
import project.infra.rdb.bondmaster.BondMasterRepository;

import java.util.Collections;
import java.util.List;

@Service
@Qualifier("bondMasterManagementService")
public class BondMasterManagementServiceImpl implements BondMasterManagementService{
    /** 債権情報管理のレポジトリクラス*/
    @Autowired
    private BondMasterRepository bondMasterRepository;

    /**
     * <p>
     *     債券マスタのデータをすべて返却する。
     *     その際に削除フラグは無視される。
     * </p>
     * @return
     */
    public List<BondMaster> findAll(){
        List<BondMaster> result = this.bondMasterRepository.findAll();
        return result == null ? Collections.emptyList() : result;
    }
}
