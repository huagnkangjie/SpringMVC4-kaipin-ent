/**
 * 
 */
/**
 * @author tan
 *
 */
package com.kaipin.search.core.lucene;

/**
 * SELECT
    *
FROM
    company_info
WHERE
    `id` IN (
        'cfaadef5-b134-4109-a5fb-7e8a7e2c75a5',
        'b890832e-3461-4f3a-a520-7cd04925c9d8',
        '8300db17-202a-48b3-9305-cb5c2e67de06',
        'ac8e7812-07c2-4608-842c-3d554c90f47f'
    )
ORDER BY
    instr(
        'cfaadef5-b134-4109-a5fb-7e8a7e2c75a5,
        b890832e-3461-4f3a-a520-7cd04925c9d8,
        8300db17-202a-48b3-9305-cb5c2e67de06,ac8e7812-07c2-4608-842c-3d554c90f47f',
        `id`
    )
 */
 